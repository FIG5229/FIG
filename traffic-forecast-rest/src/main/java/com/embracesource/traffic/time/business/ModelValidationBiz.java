package com.embracesource.traffic.time.business;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.screen.domain.HitResult;
import com.embracesource.traffic.time.domain.HitRate;
import com.embracesource.traffic.time.dto.*;
import com.embracesource.traffic.time.service.ModelValidationService;
import com.embracesource.traffic.base.utils.OneWeekUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ：tom.fu
 * @date ：Created in 2021/5/20
 * @description:
 * @version:
 */

@Service
public class ModelValidationBiz {

    @Autowired
    ModelValidationService modelValidationService;

    /**
     * 规则修正上面列表数据
     *
     * @param modelValidationRequestDTO
     * @return
     */
    public Result<ModelValidationDTO> getModelValidation(@Valid ModelValidationRequestDTO modelValidationRequestDTO) {

        List<ModelValidationResponseDTO> list = modelValidationService.getRuleAmendmentList(modelValidationRequestDTO.getCurrentStand(), modelValidationRequestDTO.getStartingStand(), modelValidationRequestDTO.getEndStand(), modelValidationRequestDTO.getPageSize(), modelValidationRequestDTO.getPage(),modelValidationRequestDTO.getSort());

        Collections.sort(list, new ListComparator());
        Integer pageCount = modelValidationService.getRuleAmendmentCount(modelValidationRequestDTO.getCurrentStand(), modelValidationRequestDTO.getStartingStand(), modelValidationRequestDTO.getEndStand(), modelValidationRequestDTO.getPageSize());
        if ((list == null || list.size() == 0) && (pageCount == null || pageCount == 0)) {
            return Result.ok(new ModelValidationDTO(0, null));
        } else {
            return Result.ok(new ModelValidationDTO(pageCount, list));
        }
    }

    /**
     * 模型验证-规则检验-时间偏差加大区间柱状图
     */
    public Result<List<SectionResponseDTO>> getSectionList() {
        String date = getDate();
        List<SectionResponseDTO> sectionList = modelValidationService.getSectionList(date);
        return Result.ok(sectionList);
    }

    /**
     * 模型验证-规则检验-时间偏差加大车站柱状图
     */
    public Result<List<StationResponseDTO>> getStationList() {
        String date = getDate();
        List<StationResponseDTO> sectionList = modelValidationService.getStationList(date);
        return Result.ok(sectionList);
    }

    /**
     * 获取系统日期-七天前时间
     *
     * @return
     */
    public static String getDate() {
        Date d = new Date();
        SimpleDateFormat sbf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sbf.format(d);
        String hourMinuteSecond = format.substring(10, format.length());
        String yearMonthDay = OneWeekUtil.lastWeek();
        String time = yearMonthDay + hourMinuteSecond;

        return time;
    }

    /**
     * 命中率
     *
     * @return
     */
    Integer isHit = 1;//记录for循环中isHit值，用于判断是否未命中
    String currentStation_temp = "";//记录is_hit = 0 的当前站名
    String carNo_temp = "";//记录is_hit = 0 的车次
    String startStation_temp = "";//记录is_hit = 0 的起始站名
    String endStation_temp = "";//记录is_hit = 0 的终点站名

    public Result<List<HitRate>> getHitRateList() {

        List<HitRate> hitRateList = modelValidationService.seleceHitRatelist();
        if(hitRateList != null && hitRateList.size() > 0) {
            return Result.ok(hitRateList);
        }else{
            String date = getDate();
            List<HitResult> notHitAllList = modelValidationService.getNotHitAllList(date);

            Map<String, Integer> notIsHitMap = new HashMap<>();//记录站名预测失败次数   key 站名  value  次数
            notHitAllList.stream().forEach(hitResult -> {

                String isHitOne = hitResult.getIsHit();//获取当前数据命中状态
                String currentStation = hitResult.getCurrentStation();//获取当前站名
                String carNo = hitResult.getCarNo();//车次
                String startStation = hitResult.getStartStation();//起始站
                String endStation = hitResult.getEndStation();//终点站
                if (isHit == 0 && !currentStation_temp.equals(currentStation) && carNo_temp.equals(carNo) && startStation_temp.equals(startStation) && endStation_temp.equals(endStation)) {
                    // 上次循环数据isHit = 0 且 当前数据当前站名不等于上一次循环当前站名
                    // 因此判断当前站为预测失败站
                    if (notIsHitMap.get(notIsHitMap) != null) {
                        Integer isNoHitNum = notIsHitMap.get(notIsHitMap) + 1;
                        notIsHitMap.put(currentStation, isNoHitNum);
                    } else {
                        notIsHitMap.put(currentStation, 1);
                    }
                    isHit = 1;
                }
                if ("0".equals(isHitOne)) {
                    isHit = 0;
                    currentStation_temp = currentStation;
                    carNo_temp = carNo;
                    startStation_temp = startStation;
                    endStation_temp = endStation;
                }
            });
            List<Map<String, Object>> countHitList = modelValidationService.getCountHitList(date);
            List<HitRate> hitRateResponseDTOList = new ArrayList<>();

            countHitList.stream().forEach(stringIntegerMap -> {
                HitRate hitRateResponseDTO = new HitRate();
                Number count = (Number) stringIntegerMap.get("cou");
                String currentStation = (String) stringIntegerMap.get("currentstation");

                Integer notHit = notIsHitMap.get(currentStation);
                Double hitRate = null;
                if(notHit != null) {
                    hitRate = notHit.doubleValue() / count.intValue();
                }else{
                    hitRate = 0D;
                }
                Double intHitRate = hitRate * 100;
                hitRateResponseDTO.setHitRate(intHitRate.intValue());
                hitRateResponseDTO.setCurrentStation(currentStation);
                hitRateResponseDTOList.add(hitRateResponseDTO);
            });

            List<HitRate> resultHitRateResponseDTOList = null;
            if (hitRateResponseDTOList != null && hitRateResponseDTOList.size() > 0) {
                hitRateResponseDTOList.sort((o1, o2) -> o2.getHitRate().compareTo(o1.getHitRate()));
                if (hitRateResponseDTOList.size() >= 10) {
                    resultHitRateResponseDTOList = hitRateResponseDTOList.subList(0, 10);
                } else {
                    resultHitRateResponseDTOList = hitRateResponseDTOList;
                }
            }

            modelValidationService.deleteAll();

            modelValidationService.saveBatch(resultHitRateResponseDTOList);

            return Result.ok(resultHitRateResponseDTOList);
        }
    }
}

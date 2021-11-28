package com.embracesource.traffic.predict.task;

import com.embracesource.traffic.base.utils.DateUtil;
import com.embracesource.traffic.base.utils.OneWeekUtil;
import com.embracesource.traffic.predict.domain.AHitDistinct2;
import com.embracesource.traffic.predict.domain.AHitDistinct3;
import com.embracesource.traffic.predict.service.AHitDistinct2Service;
import com.embracesource.traffic.predict.service.AHitDistinct3Service;
import com.embracesource.traffic.predict.service.AHitDistinctService;
import com.embracesource.traffic.screen.domain.HitResult;
import com.embracesource.traffic.time.business.ModelValidationBiz;
import com.embracesource.traffic.time.domain.HitRate;
import com.embracesource.traffic.time.dto.HitRateResponseDTO;
import com.embracesource.traffic.time.dto.HitRateResponseListDTO;
import com.embracesource.traffic.time.service.ModelValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author: daniel.liu
 * @Description:
 * @Date: create in 2021/5/26 13:58
 */
@Slf4j
@Component
public class TaskUtils {

    @Autowired
    AHitDistinctService aHitDistinctService;

    @Autowired
    AHitDistinct2Service aHitDistinct2Service;

    @Autowired
    AHitDistinct3Service aHitDistinct3Service;

    @Autowired
    ModelValidationService modelValidationService;


//    @Scheduled(cron = "0 */3 * * * ?")
//    public void test(){
//        log.error("*******************************************************\n" +
//                "               定时任务执行\n"+"" +
//                "\"*******************************************************\n");
//    }

    /**
     * 定时获取预测数据 每小时执行一次
     */
    /*停掉 数据库那边定时获取了 一天两次早上 和中午*/
//    @Scheduled(cron = "0 0 */1 * * ?")
    public void addDataToAHitDistinct2(){
        Date startDate = DateUtil.getLastHourTime(new Date(),1);
        Date endDate = new Date();
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        List<AHitDistinct2> aHitDistinct2List = aHitDistinctService.queryPredictHitDistinct2(startTime,endTime,startDate,endDate);
        Date queryDate = new Date();
        aHitDistinct2Service.saveBatch(aHitDistinct2List);
        Date insertDate = new Date();
        log.error("\n查询时间-------------》"+(queryDate.getTime()-endTime)/1000);
        log.error("\n写入时间-------------》"+(insertDate.getTime()-queryDate.getTime())/1000);
    }


    /**
     * 定时获取预测数据
     */
    /*停掉 数据库那边定时获取了 一天两次早上 和中午*/
//    @Scheduled(cron = "0 0 */1 * * ?")
    public void addDataToAHitDistinct3(){
        Date startDate = DateUtil.getLastHourTime(new Date(),1);
        Date endDate = new Date();
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        List<AHitDistinct3> aHitDistinct3List = aHitDistinctService.queryPredictHitDistinct3(startTime,endTime,startDate,endDate);
        aHitDistinct3Service.saveBatch(aHitDistinct3List);

    }

    /**
     * 获取模型分析命中率  每30分钟执行一次
     */
    Integer isHit = 1;//记录for循环中isHit值，用于判断是否未命中
    String currentStation_temp = "";//记录is_hit = 0 的当前站名
    String carNo_temp = "";//记录is_hit = 0 的车次
    String startStation_temp = "";//记录is_hit = 0 的起始站名
    String endStation_temp = "";//记录is_hit = 0 的终点站名

    @Scheduled(cron = "0 */30 * * * ?")
    public void getHitRate(){
        String date = ModelValidationBiz.getDate();
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
            System.out.println(intHitRate);
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
    }
}

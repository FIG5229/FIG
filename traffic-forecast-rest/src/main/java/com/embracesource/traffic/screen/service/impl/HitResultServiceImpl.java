package com.embracesource.traffic.screen.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.embracesource.traffic.base.utils.DateUtil;
import com.embracesource.traffic.forecast.dao.GisStationCltsZdDao;
import com.embracesource.traffic.forecast.domain.GisStationCltsZdDO;
import com.embracesource.traffic.screen.dao.HitResultDao;
import com.embracesource.traffic.screen.dto.CountInfoDTO;
import com.embracesource.traffic.screen.dto.TotalStatisticsResponseDTO;
import com.embracesource.traffic.screen.dto.TrainsErrorSiteDTO;
import com.embracesource.traffic.screen.dto.UnusualForecastDataNumResponseDTO;
import com.embracesource.traffic.screen.service.HitResultService;
import com.embracesource.traffic.screen.vo.DateVo;
import com.embracesource.traffic.datamanage.dto.HitResultRequestDTO;
import com.embracesource.traffic.datamanage.dto.HitResultResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.embracesource.traffic.base.config.Constant.WEEK;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-25 下午 04:17
 * @description：
 * @version:
 */
@Service
@DS("pgsql")
public class HitResultServiceImpl implements HitResultService {
    @Autowired
    HitResultDao hitResultDao;
    @Autowired
    GisStationCltsZdDao gisStationCltsZdDao;

    @Override
    public Long countAmount(String startTime, String endTime) {
        return hitResultDao.countAmount(startTime, endTime);
    }

    @Override
    public Long countErrorAmount(String startTime, String endTime) {
        return hitResultDao.countErrorAmount(startTime, endTime);
    }

    @Override
    public Float countErrorAmountdivideCountAmount() {
        Long countAmount = hitResultDao.countAmount(null, null);
        Long countErrorAmount = hitResultDao.countErrorAmount(null, null);
        Float aFloat = new Float(countAmount);
        return countErrorAmount / aFloat;
    }

    @Override
    public Float perDayCountErrorAmountdivideCountAmount() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        String minTimes = c.getTimeInMillis() + "";
        c.add(Calendar.DATE, 1);
        String maxTimes = c.getTimeInMillis() + "";
        Long countAmount = hitResultDao.countAmount(minTimes, maxTimes);
        Long countErrorAmount = hitResultDao.countErrorAmount(minTimes, maxTimes);
        Float aFloat = new Float(countAmount);
        return countErrorAmount / aFloat;
    }

    @Override
    public List<TrainsErrorSiteDTO> queryErrorListTopTenByStartAndEnd() {
        List<TrainsErrorSiteDTO> trainsErrorSiteDTOS = hitResultDao.queryErrorListTopTenByStartAndEnd(null, null);
        if (CollectionUtils.isEmpty(trainsErrorSiteDTOS)) {
            return null;
        }
        List<String> currents = trainsErrorSiteDTOS.stream().map(TrainsErrorSiteDTO::getCurrentStation).collect(Collectors.toList());
        List<GisStationCltsZdDO> allByDblmIn = gisStationCltsZdDao.findAllByDblmIn(currents);
        Map<String, String> currentNameMap = allByDblmIn.stream().collect(Collectors.toMap(GisStationCltsZdDO::getDblm, GisStationCltsZdDO::getName));
        trainsErrorSiteDTOS.forEach(trainsErrorSiteDTO -> {
            String name = currentNameMap.get(trainsErrorSiteDTO.getCurrentStation());
            if (!StringUtils.isEmpty(name)) {
                trainsErrorSiteDTO.setCurrentStation(name);
            }
        });
        return trainsErrorSiteDTOS;
    }

    @Override
    public HashMap<Integer, Float> getAccountMap() {
        //TODO 获取准确率统计月
        List<String> months = DateUtil.getBeforeMonth(30);
        DateVo monthBeginAndEnd = DateUtil.getMonthBeginAndEnd();
        HashMap<Integer, Float> accountMap = new HashMap<>();
        return accountMap;
    }

    //实时模型预测异常数量
    @Override
    public List<Map<String,Object>> getRealTimeModelErrorNumber(List<String> beforeHour, Date currentHourBegin, Date currentHourEnd) {
        //Map<Integer, Long> result = new HashMap<>(8);
        //TODO 获取历史数据
        List<Map<String,Object>>  resultList = hitResultDao.getRealTimeModelErrorNumber(beforeHour);
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTime(currentHourBegin);
        //result.put(calendar.get(Calendar.HOUR_OF_DAY), hitResultDao.countErrorAmount(DateUtil.dateToMillisString(currentHourBegin), DateUtil.dateToMillisString(currentHourEnd)));
        return resultList;
    }
    @Override
    public Map<String,Object> getRealTimeModelErrorNumber_new(String now,String after,String format) {
        Map<String,Object>  resultMap = hitResultDao.getRealTimeModelErrorNumber_new(now,after,format);
       return resultMap;
    }

    @Override
    public List<CountInfoDTO> getPredictiveFailureAmount(Date startTime, Date endTime) {
        List<CountInfoDTO> res = hitResultDao.countErrorAmountBeta(startTime.getTime()+"",endTime.getTime()+"");
        for (int i =0;i<WEEK;i++){
            Date date = DateUtil.getZeroTime(-i);
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.FORMATDATE1);
            String time = sdf.format(date);
            String day = time.substring(5,10);
            List<String> labelList = res.stream().map(CountInfoDTO::getLabel).collect(Collectors.toList());
            if (!labelList.contains(day)){
                CountInfoDTO dto = new CountInfoDTO();
                dto.setLabel(day);
                dto.setCount(0);
                res.add(i,dto);

            }

        }

        return res;
    }

    //命中数据页面展示列表
    @Override
    public List<HitResultResponseDTO> getHitResultList(HitResultRequestDTO hitResultRequestDTO, int startIndex, int pageSize) {
        return hitResultDao.getHitResultList(hitResultRequestDTO,startIndex,pageSize);
    }

    @Override
    public List<UnusualForecastDataNumResponseDTO> getUnusualForecastDataNumList(List<String> latest12Month) {
        return hitResultDao.getUnusualForecastDataNumList(latest12Month);
    }
    @Override
    public List<UnusualForecastDataNumResponseDTO> getUnusualForecastDataNumList_new() {
        return hitResultDao.getUnusualForecastDataNumList_new();
    }

    //实时模型输入数量
    @Override
    public List<Map<String, Object>> getRealTimeModelInputNumber(List<String> beforeHour, Date currentHourBegin, Date currentHourEnd) {
        //获取前8个小时（包含当前时）实时模型输入数量
        List<Map<String,Object>> resultList = hitResultDao.getRealTimeModelInputNumber(beforeHour);
        return resultList;
    }
    @Override
    public Map<String, Object> getRealTimeModelInputNumber_new(String now,String after,String format) {
        //获取前8个小时（包含当前时）实时模型输入数量
        Map<String,Object> resultMap = hitResultDao.getRealTimeModelInputNumber_new(now,after,format);
        return resultMap;
    }

    //实时模型预测数量
    @Override
    public List<Map<String, Object>> getRealTimeModelPredicts(List<String> beforeHour, Date currentHourBegin, Date currentHourEnd) {
        //获取前8个小时（包含当前时）实时模型预测数量
        List<Map<String,Object>>  resultList = hitResultDao.getRealTimeModelPredictsNumber(beforeHour);
        return resultList;
    }
    @Override
    public Map<String, Object> getRealTimeModelPredicts_new(String now, String after, String format) {
        //获取前8个小时（包含当前时）实时模型预测数量
        Map<String,Object> resultMap = hitResultDao.getRealTimeModelPredictsNumber_new(now,after,format);
        return resultMap;
    }

    @Override
    public int getHitResultListCount(HitResultRequestDTO hitResultRequestDTO) {
        return hitResultDao.getHitResultListCount(hitResultRequestDTO);
    }

    @Override
    public List<Map<String, Object>> getRealTimeModelInputNumberByTimeSlot(String beforeTime, String nowTime) {
        //获取前8个小时（包含当前时）实时模型输入数量
        List<Map<String,Object>> resultList = hitResultDao.getRealTimeModelInputNumberByTimeSlot(beforeTime,nowTime);
        return resultList;
    }

    @Override
    public List<Map<String, Object>> getRealTimeModelPredictsByTimeSlot(String beforeTime, String nowTime) {
        //获取前8个小时（包含当前时）实时模型预测数量
        List<Map<String,Object>>  resultList = hitResultDao.getRealTimeModelPredictsByTimeSlot(beforeTime,nowTime);
        return resultList;
    }

    @Override
    public List<Map<String, Object>> getRealTimeModelErrorNumberByTimeSlot(String beforeTime, String nowTime) {
        List<Map<String,Object>>  resultList = hitResultDao.getRealTimeModelErrorNumberByTimeSlot(beforeTime,nowTime);
        return resultList;
    }

    /**
     * 总量统计接口
     * 表：view_total_statistics
     */
    @Override
    public List<TotalStatisticsResponseDTO> getTotalStatisticsList() {
        List<TotalStatisticsResponseDTO> resultList = hitResultDao.getTotalStatisticsList();
        return resultList;
    }
}

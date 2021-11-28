package com.embracesource.traffic.screen.service;

import com.embracesource.traffic.screen.dto.TotalStatisticsResponseDTO;
import com.embracesource.traffic.screen.dto.CountInfoDTO;
import com.embracesource.traffic.screen.dto.TrainsErrorSiteDTO;
import com.embracesource.traffic.datamanage.dto.HitResultRequestDTO;
import com.embracesource.traffic.datamanage.dto.HitResultResponseDTO;
import com.embracesource.traffic.screen.dto.UnusualForecastDataNumResponseDTO;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-25 下午 04:16
 * @description：
 * @version:
 */
public interface HitResultService {
    /**
     * 获取总量
     */
    Long countAmount(String startTime, String endTime);

    /**
     * 获取错误数总量
     */
    Long countErrorAmount(String startTime, String endTime);

    /**
     * 总的错误数量/总数量
     */
    Float countErrorAmountdivideCountAmount();


    /**
     * 当天的错误数量/当天的总数量
     */
    Float perDayCountErrorAmountdivideCountAmount();

    /**
     * 获取总的异常预测站点top10
     */
    List<TrainsErrorSiteDTO> queryErrorListTopTenByStartAndEnd();

    HashMap<Integer, Float> getAccountMap();

    //获取最近八小时实时模型预测异常数量
    List<Map<String,Object>> getRealTimeModelErrorNumber(List<String> beforeHour, Date currentHourBegin, Date currentHourEnd);
    Map<String, Object> getRealTimeModelErrorNumber_new(String now, String after, String format);
    /**
     * --预测失败量   一条线展示
     */
    List<CountInfoDTO> getPredictiveFailureAmount(Date startTime, Date endTime);

    //命中数据，页面列表展示
    List<HitResultResponseDTO> getHitResultList(HitResultRequestDTO hitResultRequestDTO, int startIndex, int pageSize);

    //获取近12月异常预测数据量统计
    List<UnusualForecastDataNumResponseDTO> getUnusualForecastDataNumList(List<String> latest12Month);
    List<UnusualForecastDataNumResponseDTO> getUnusualForecastDataNumList_new();
    //获取最近八小时实时模型输入数量
    List<Map<String, Object>> getRealTimeModelInputNumber(List<String> beforeHour, Date currentHourBegin, Date currentHourEnd);
    Map<String, Object> getRealTimeModelInputNumber_new(String now, String after, String format);

    List<Map<String, Object>> getRealTimeModelPredicts(List<String> beforeHour, Date currentHourBegin, Date currentHourEnd);
    Map<String, Object> getRealTimeModelPredicts_new(String now, String after, String format);
    int getHitResultListCount(HitResultRequestDTO hitResultRequestDTO);
    //获取最近八小时实时模型输入数量根据时间段
    List<Map<String, Object>> getRealTimeModelInputNumberByTimeSlot(String beforeTime, String nowTime);
    //获取最近八小时实时模型预测数量根据时间段
    List<Map<String, Object>> getRealTimeModelPredictsByTimeSlot(String beforeTime, String nowTime);
    //获取最近八小时实时模型异常数量根据时间段
    List<Map<String, Object>> getRealTimeModelErrorNumberByTimeSlot(String beforeTime, String nowTime);
    //总量统计
    List<TotalStatisticsResponseDTO> getTotalStatisticsList();
}

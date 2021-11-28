package com.embracesource.traffic.screen.service;

import com.embracesource.traffic.base.pageHelper.PageInfo;
import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.screen.domain.CarResultInfoHistory;
import com.embracesource.traffic.screen.dto.*;


import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-25 下午 03:27
 * @description：
 * @version:
 */
public interface CarResultInfoHistoryService {

    /**
     * 获取某天的日运行车次数据
     * @return
     */
    long countByDayDate(Date day);

    /**
     * 获取某天的日运行车次的全部数据
     * @return
     */
    List<CarResultInfoHistory> queryListByDayDate(Date day);

    /**
     *今日运行车次数量
     */
    long countCarNoByNowDayDate(Date day);

    Map<Integer, Long> getHourForecastMap();

    Map<Integer, Long> getRealTimeModelInputNumber(List<String> beforeHour, Date currentHourBegin, Date currentHourEnd);

    Map<Integer, Long> getRealTimeModelPredicts(List<String> beforeHour, Date currentHourBegin, Date currentHourEnd);

    List<VehicleRunningStatusNewDTO> getRunStatus_new(int topk);

    List<VehicleRunningStatusDTO> getRunStatus(int topk);

    List<VehicleRunningStatusDTO> getRunAllStatus(String carNo, String startStation, String endStation, String goodsType);

    /**
     *  数据处理量   分别查询最近7天的数据  出发 到达 车号识别   三条线展示
     */
    List<CountDataDTO> countDataHandlingCapacityDay(Date startTime,Date endTime);

    Result queryListPredict(Date startTime, Date endTime, PredictCarResultRequestDTO requestDTO);


    List<Map<String, Object>> getCanalTraffic();

    ////站点通行能力（TOP10）
    List<SitTrafficTopTenResponseDTO> getSitTrafficTopTen();

    List<CountInfoDTO> getHourForecastList(String lastHourTime, String date);
}

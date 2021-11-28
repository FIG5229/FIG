package com.embracesource.traffic.screen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.screen.domain.CarResultInfoHistory;
import com.embracesource.traffic.screen.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-25 下午 03:25
 * @description：
 * @version:
 */
@Mapper
public interface CarResultInfoHistoryDao extends BaseMapper<CarResultInfoHistory> {
    long countByStartDateAndEndDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<CarResultInfoHistory> queryListByNowDayDate(@Param("startTime") Date startTime,
                                                     @Param("endTime") Date endTime);
    long countCarNoByNowDayDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    Long countPredictsByStartDateAndEndDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<CountInfoDTO> getHourForecastList(@Param("startDate") String startTime, @Param("endDate") String endTime);


    List<VehicleRunningStatusNewDTO> queryListByTopK_new(@Param("topk") int topk);
    List<VehicleRunningStatusNewDTO> queryListByTopK_new2(@Param("topk") int topk, @Param("format")String format);

    List<VehicleRunningStatusDTO> queryListByTopK(@Param("topk") int topk);

    List<VehicleRunningStatusDTO> queryListByCarNoAndStartAndEndAndGoodType(@Param("carNo") String carNo,
                                                                            @Param("startStation") String startStation,
                                                                            @Param("endStation") String endStation,
                                                                            @Param("goodsType") String goodsType);

    List<DataCountInfoDTO> countDataHandlingCapacityDay(@Param("startTime") Date startTime, @Param("endTime")Date endTime,
                                                    @Param("type")String type);

    long getDataHandlingCapacityDay(@Param("startTime") Date startTime, @Param("endTime")Date endTime);

    List<PredictCarResultDTO> queryListPredict(@Param("carResult")PredictCarResultRequestDTO requestDTO, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<Map<String,Object>> countCanalTraffic();

    //站点通行能力（TOP10）
    int getSitTrafficToday();
    List<SitTrafficTopTenResponseDTO> getSitTrafficTopTen(@Param("totalNum") int totalNum);


}

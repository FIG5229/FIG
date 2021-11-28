package com.embracesource.traffic.predict.dao;

import com.embracesource.traffic.predict.domain.AHitDistinct2;
import com.embracesource.traffic.predict.domain.AHitDistinct3;
import com.embracesource.traffic.screen.dto.CountInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: daniel.liu
 * @Description:
 * @Date: create in 2021/5/24 11:23
 */
@Mapper
public interface AHitDistinctDao {
    /*实时预测命中率*/
    Map<String,Long> countPredictHitRate(@Param("startTime")Long startTime,
                               @Param("endTime")Long endTime,
                               @Param("startDate")Date startDate,
                               @Param("endDate") Date endDate);

    /*实时预测准确率*/
    Map<String,Long> countPredictAccuracy(@Param("startTime")Long startTime,
                                @Param("endTime")Long endTime,
                                @Param("startDate")Date startDate,
                                @Param("endDate") Date endDate);

    Long countOnLinePredictNum(@Param("startTime")Long startTime,
                               @Param("endTime")Long endTime,
                               @Param("startDate")Date startDate,
                               @Param("endDate") Date endDate,
                               @Param("isHit") String isHit);

    List<CountInfoDTO> countDistributionAnomalyPredictionSitesTop();



    int addPredictHitDistinct2(AHitDistinct2 aHitDistinct2);

    List<AHitDistinct2> queryPredictHitDistinct2(@Param("startTime")Long startTime,
                                                 @Param("endTime")Long endTime,
                                                 @Param("startDate")Date startDate,
                                                 @Param("endDate") Date endDate);


    List<AHitDistinct3> queryPredictHitDistinct3(@Param("startTime")Long startTime,
                                                 @Param("endTime")Long endTime,
                                                 @Param("startDate")Date startDate,
                                                 @Param("endDate") Date endDate);
}

package com.embracesource.traffic.predict.service;

import com.embracesource.traffic.predict.domain.AHitDistinct2;
import com.embracesource.traffic.predict.domain.AHitDistinct3;
import com.embracesource.traffic.screen.dto.CountInfoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: daniel.liu
 * @Description:
 *  用来查询实时数据
 *     每个sql都挺慢
 * @Date: create in 2021/5/24 11:25
 */
public interface AHitDistinctService {

    /*预测命中率*/
    Map<String,Long> countPredictHitRate(Date startDate,Date endDate);

    /*预测准确率*/
    Map<String,Long> countPredictAccuracy(Date startDate,Date endDate);

    /*异常预测统计*/
    Long countOnLinePredictNum(Date startDate,Date endDate ,String isHit);


    List<CountInfoDTO> getDistributionAnomalyPredictionSitesTop();



    int addPredictHitDistinct2(AHitDistinct2 aHitDistinct2);

    List<AHitDistinct2> queryPredictHitDistinct2(Long startTime,Long endTime, Date startDate,  Date endDate);

    List<AHitDistinct3> queryPredictHitDistinct3(Long startTime,Long endTime, Date startDate,  Date endDate);
}
;
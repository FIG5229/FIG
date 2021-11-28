package com.embracesource.traffic.predict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.embracesource.traffic.predict.domain.AHitDistinct2;
import com.embracesource.traffic.screen.dto.CountInfoDTO;

import java.util.List;
import java.util.Map;

public interface AHitDistinct2Service extends IService<AHitDistinct2> {

    Map<String,Long> countOffLineHitRate(Long startTime, Long endTime);
    /**/
    Map<String,Long> countOffLineAccuracy(Long startTime, Long endTime);

    List<CountInfoDTO> getOffLineDistributionAnomalyPredictionSitesTop(Long startTime, Long endTime);


    Long countOffLinePredictNum(Long startTime, Long endTime, String s);

    List<Map<String ,Object>> countOffLineAccuracyDay(Long startTime, Long endTime);
}

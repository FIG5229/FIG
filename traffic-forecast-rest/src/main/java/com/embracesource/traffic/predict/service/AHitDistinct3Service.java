package com.embracesource.traffic.predict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.embracesource.traffic.predict.domain.AHitDistinct2;
import com.embracesource.traffic.predict.domain.AHitDistinct3;
import com.embracesource.traffic.screen.dto.CountInfoDTO;

import java.util.List;
import java.util.Map;

public interface AHitDistinct3Service extends IService<AHitDistinct3> {

    /*路局预测命中率*/
    List<Map<String, Object>> countOffLinePredictHitRate(Long startTime, Long endTime);

    /*路局预测准确率*/
    List<Map<String, Object>> countOffLinePredictAccuracy(Long startTime, Long endTime);
}

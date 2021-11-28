package com.embracesource.traffic.predict.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.embracesource.traffic.predict.dao.AHitDistinct2Dao;
import com.embracesource.traffic.predict.domain.AHitDistinct2;
import com.embracesource.traffic.predict.service.AHitDistinct2Service;
import com.embracesource.traffic.screen.dto.CountInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: daniel.liu
 * @Description:
 * @Date: create in 2021/5/26 14:24
 */
@Service
public class AHitDistinct2ServiceImpl extends ServiceImpl<AHitDistinct2Dao, AHitDistinct2> implements AHitDistinct2Service {

    @Override
    public Map<String,Long> countOffLineHitRate(Long startTime, Long endTime) {
        return baseMapper.countOffLineHitRate(startTime,endTime);
    }

    @Override
    public Map<String,Long> countOffLineAccuracy(Long startTime, Long endTime) {
        return baseMapper.countOffLineAccuracy(startTime,endTime);
    }

    @Override
    public List<CountInfoDTO> getOffLineDistributionAnomalyPredictionSitesTop(Long startTime, Long endTime) {
        return baseMapper.countOffLineDistributionAnomalyPredictionSitesTop(startTime,endTime);
    }

    @Override
    public Long countOffLinePredictNum(Long startTime, Long endTime, String isHit) {
        return baseMapper.countOffLinePredictNum(startTime,endTime,isHit);
    }

    @Override
    public List<Map<String ,Object>> countOffLineAccuracyDay(Long startTime, Long endTime) {
        return baseMapper.countOffLineAccuracyDay(startTime,endTime);
    }
}

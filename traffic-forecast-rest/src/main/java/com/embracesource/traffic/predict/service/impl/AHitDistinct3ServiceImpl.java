package com.embracesource.traffic.predict.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.embracesource.traffic.predict.dao.AHitDistinct2Dao;
import com.embracesource.traffic.predict.dao.AHitDistinct3Dao;
import com.embracesource.traffic.predict.domain.AHitDistinct2;
import com.embracesource.traffic.predict.domain.AHitDistinct3;
import com.embracesource.traffic.predict.service.AHitDistinct2Service;
import com.embracesource.traffic.predict.service.AHitDistinct3Service;
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
public class AHitDistinct3ServiceImpl extends ServiceImpl<AHitDistinct3Dao, AHitDistinct3> implements AHitDistinct3Service {

    @Autowired
    AHitDistinct3Dao aHitDistinct3Dao;

    @Override
    public List<Map<String, Object>> countOffLinePredictHitRate(Long startTime, Long endTime) {
        return aHitDistinct3Dao.countOffLinePredictHitRate(startTime,endTime);

    }

    @Override
    public List<Map<String, Object>> countOffLinePredictAccuracy(Long startTime, Long endTime) {
        return aHitDistinct3Dao.countOffLinePredictAccuracy(startTime,endTime);
    }
}

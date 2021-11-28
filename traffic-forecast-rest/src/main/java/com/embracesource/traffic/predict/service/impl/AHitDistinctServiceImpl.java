package com.embracesource.traffic.predict.service.impl;

import com.embracesource.traffic.predict.dao.AHitDistinctDao;
import com.embracesource.traffic.predict.domain.AHitDistinct2;
import com.embracesource.traffic.predict.domain.AHitDistinct3;
import com.embracesource.traffic.screen.dto.CountInfoDTO;
import com.embracesource.traffic.predict.service.AHitDistinctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: daniel.liu
 * @Description:
 * @Date: create in 2021/5/24 11:26
 */
@Service
public class AHitDistinctServiceImpl implements AHitDistinctService {

    @Autowired
    AHitDistinctDao aHitDistinctDao;

    @Override
    public Map<String,Long> countPredictHitRate(Date startDate,Date endDate) {
        return aHitDistinctDao.countPredictHitRate(startDate.getTime(),endDate.getTime(),startDate,endDate);
    }

    @Override
    public Map<String,Long> countPredictAccuracy(Date startDate,Date endDate) {
        return aHitDistinctDao.countPredictAccuracy(startDate.getTime(),endDate.getTime(),startDate,endDate);
    }

    @Override
    public Long countOnLinePredictNum(Date startDate,Date endDate,String isHit) {
        return aHitDistinctDao.countOnLinePredictNum(startDate.getTime(),endDate.getTime(),startDate,endDate,isHit);
    }

    @Override
    public List<CountInfoDTO> getDistributionAnomalyPredictionSitesTop() {
        return aHitDistinctDao.countDistributionAnomalyPredictionSitesTop();
    }



    @Override
    public int addPredictHitDistinct2(AHitDistinct2 aHitDistinct2) {
        return aHitDistinctDao.addPredictHitDistinct2(aHitDistinct2);
    }

    @Override
    public List<AHitDistinct2> queryPredictHitDistinct2(Long startTime,Long endTime,Date startDate, Date endDate) {
        return aHitDistinctDao.queryPredictHitDistinct2(startTime,endTime,startDate,endDate);
    }

    @Override
    public List<AHitDistinct3> queryPredictHitDistinct3(Long startTime,Long endTime,Date startDate, Date endDate) {
        return aHitDistinctDao.queryPredictHitDistinct3(startTime,endTime,startDate,endDate);
    }


}

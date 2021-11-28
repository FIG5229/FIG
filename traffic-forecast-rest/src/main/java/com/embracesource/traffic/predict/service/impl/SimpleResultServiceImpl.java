package com.embracesource.traffic.predict.service.impl;


import com.embracesource.traffic.predict.dao.SimpleResultDao;
import com.embracesource.traffic.predict.service.SimpleResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: daniel.liu
 * @Date: create in 2021/8/11 18:00、
 * @Description:
 */
@Service
public class SimpleResultServiceImpl implements SimpleResultService {
    @Autowired
    SimpleResultDao simpleResultDao;
    @Override
    public List<Map<String, Object>> countOffLineAccuracyDay() {
        return simpleResultDao.countOffLineAccuracy();
    }
}

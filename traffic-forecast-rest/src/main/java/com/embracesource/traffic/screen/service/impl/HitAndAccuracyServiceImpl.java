package com.embracesource.traffic.screen.service.impl;

import com.embracesource.traffic.screen.dao.HitAndAccuracyDao;
import com.embracesource.traffic.screen.dto.ResultInfoDto;
import com.embracesource.traffic.screen.service.HitAndAccuracyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: daniel.liu
 * @Date: create in 2021/7/21 10:02、
 * @Description:
 */
@Service
public class HitAndAccuracyServiceImpl implements HitAndAccuracyService {

    @Resource
    HitAndAccuracyDao hitAndAccuracyDao;

    @Override
    public List<Map<String,Object>> findHitAndAccuracy() {
        return hitAndAccuracyDao.findHitAndAccuracy();
    }
}

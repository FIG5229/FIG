package com.embracesource.traffic.screen.service;

import com.embracesource.traffic.screen.dto.ResultInfoDto;

import java.util.List;
import java.util.Map;

/**
 * @Author: daniel.liu
 * @Date: create in 2021/7/21 10:04、
 * @Description:
 */
public interface HitAndAccuracyService {
    List<Map<String,Object>> findHitAndAccuracy();
}

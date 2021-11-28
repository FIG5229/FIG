package com.embracesource.traffic.screen.dao;

import com.embracesource.traffic.screen.dto.ResultInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: daniel.liu
 * @Date: create in 2021/7/21 10:12、
 * @Description:
 */
@Mapper
public interface HitAndAccuracyDao {
    List<Map<String,Object>> findHitAndAccuracy();
}

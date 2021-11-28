package com.embracesource.traffic.predict.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.predict.domain.AHitDistinct2;
import com.embracesource.traffic.predict.domain.AHitDistinct3;
import com.embracesource.traffic.screen.dto.CountInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: daniel.liu
 * @Description:
 * @Date: create in 2021/5/26 14:25
 */
@Mapper
public interface AHitDistinct3Dao extends BaseMapper<AHitDistinct3> {
    List<Map<String, Object>> countOffLinePredictHitRate(@Param("startTime") Long startTime, @Param("endTime") Long endTime);

    List<Map<String, Object>> countOffLinePredictAccuracy(@Param("startTime") Long startTime, @Param("endTime") Long endTime);
}

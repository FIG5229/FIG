package com.embracesource.traffic.predict.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.predict.domain.AHitDistinct2;
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
public interface AHitDistinct2Dao extends BaseMapper<AHitDistinct2> {

    /*命中率 离线数据*/
    Map<String,Long> countOffLineHitRate(@Param("startTime") Long startTime, @Param("endTime")Long endTime);
    Map<String,Long> countOffLineAccuracy(@Param("startTime") Long startTime, @Param("endTime")Long endTime);

//    离线 异常预测站点分布
    List<CountInfoDTO> countOffLineDistributionAnomalyPredictionSitesTop(@Param("startTime") Long startTime, @Param("endTime")Long endTime);

    Long countOffLinePredictNum(@Param("startTime") Long startTime, @Param("endTime")Long endTime, @Param("isHit") String isHit);

    List<Map<String ,Object>> countOffLineAccuracyDay(@Param("startTime") Long startTime, @Param("endTime")Long endTime);
}

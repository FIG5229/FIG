package com.embracesource.traffic.forecast.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.forecast.domain.OfflineResultDO;
import com.embracesource.traffic.forecast.dto.OfflineResultVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：wangshimin
 * @date ：Created in 2020-11-06 上午 10:52
 * @description：
 * @version:
 */
@Mapper
public interface OfflineResultDao extends BaseMapper<OfflineResultDO> {

    List<OfflineResultVO> queryAllByStartStationAndEndStation(@Param("startStation")String startStation,
                                                              @Param("endStation")String endStation);


    List<OfflineResultVO> queryAllByStartStationAndEndStationAndCurrentStation(@Param("startStation")String startStation,
                                                                               @Param("endStation")String endStation,
                                                                               @Param("currentStation")String currentStation);


}

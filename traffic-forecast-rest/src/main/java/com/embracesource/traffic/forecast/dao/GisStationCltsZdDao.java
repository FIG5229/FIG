package com.embracesource.traffic.forecast.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.forecast.domain.GisStationCltsZdDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ：wangshimin
 * @date ：Created in 2020-11-06 上午 10:43
 * @description：
 * @version:
 */
@Mapper
public interface GisStationCltsZdDao extends BaseMapper<GisStationCltsZdDO> {

    GisStationCltsZdDO findByName(String name);

    List<GisStationCltsZdDO> findAllByNameIn(List<String> names);


    List<GisStationCltsZdDO> findAllByDblmIn(List<String> names);


}

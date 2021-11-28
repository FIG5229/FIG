package com.embracesource.traffic.forecast.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.forecast.domain.OfflineLzjDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ：wangshimin
 * @date ：Created in 2020-11-09 上午 09:32
 * @description：
 * @version:
 */
@Mapper
public interface OfflineLzjDao extends BaseMapper<OfflineLzjDO> {

    List<OfflineLzjDO> queryByCurrentStationAndNStation(List<String> keys);
}

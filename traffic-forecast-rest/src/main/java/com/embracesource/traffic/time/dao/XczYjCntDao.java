package com.embracesource.traffic.time.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.time.domain.XczYjCntDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：wangshimin
 * @date ：Created in 2021/3/23 14:43
 * @description：
 * @version:
 */
@Mapper
public interface XczYjCntDao extends BaseMapper<XczYjCntDO> {
    Long getAmountByStation(@Param("station") String station);
}

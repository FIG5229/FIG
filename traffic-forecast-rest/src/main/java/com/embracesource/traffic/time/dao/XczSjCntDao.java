package com.embracesource.traffic.time.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.time.domain.XczSjCntDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：wangshimin
 * @date ：Created in 2021/3/23 14:43
 * @description：
 * @version:
 */
@Mapper
public interface XczSjCntDao extends BaseMapper<XczSjCntDO> {
    Long getAmountByStation(@Param("station") String station);
}

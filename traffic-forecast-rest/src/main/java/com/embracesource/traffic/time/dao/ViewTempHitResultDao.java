package com.embracesource.traffic.time.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.time.domain.ViewTempHitResultDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：wangshimin
 * @date ：Created in 2021/3/22 16:37
 * @description：
 * @version:
 */
@Mapper
public interface ViewTempHitResultDao extends BaseMapper<ViewTempHitResultDO> {
    List<ViewTempHitResultDO> queryHitResultDOByTableNameAndVid(@Param("tabName") String tabName, @Param("vId") String vId);
}

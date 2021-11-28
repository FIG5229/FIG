package com.embracesource.traffic.time.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.time.domain.ViewTimeErrorResultDo;
import com.embracesource.traffic.time.domain.ViewTimePageNumDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.Map;

@Mapper
public interface ViewTimeErrorResultDao extends BaseMapper<ViewTimeErrorResultDo> {

    ViewTimeErrorResultDo getAccrate(@Param("id") String id);

    Long getCarCount(@Param("nowDate") Timestamp nowDate, @Param("zeroDate") Timestamp zeroDate);
    Long getLeaveCount(@Param("nowDate") Timestamp nowDate,@Param("zeroDate") Timestamp zeroDate);
    Long getArriveCount(@Param("nowDate") Timestamp nowDate,@Param("zeroDate") Timestamp zeroDate);

    ViewTimePageNumDo getCount();
}

package com.embracesource.traffic.time.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.time.domain.ViewTimeLjcomeDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ViewTimeLjcomeDao extends BaseMapper<ViewTimeLjcomeDo> {
    ViewTimeLjcomeDo getLjAccrate(@Param("id") String id, @Param("station") String station, @Param("ljName") String ljName);
    List<ViewTimeLjcomeDo> getLjAccrateList(@Param("id") String id, @Param("station")String station);
}

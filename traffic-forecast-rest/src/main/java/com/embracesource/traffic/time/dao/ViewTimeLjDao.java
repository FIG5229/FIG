package com.embracesource.traffic.time.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.time.domain.ViewTimeLjDo;
import com.embracesource.traffic.time.dto.CoordinateDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface ViewTimeLjDao extends BaseMapper<ViewTimeLjDo> {
    ViewTimeLjDo getAllLjAccrate(@Param("id") String id, @Param("station") String station);

    List<ViewTimeLjDo> getAllStationAllLjAccrate(@Param("id") String id);

    //拿到编组站和卸车站的经度和纬度
    CoordinateDTO getCoordinateBZZ(@Param("station") String station);

    CoordinateDTO getCoordinateXCZ(@Param("station") String station);

    List<CoordinateDTO> queryCoordinateXCZ(@Param("stationSet") Set<String> stationSet);

    List<CoordinateDTO> queryCoordinateBZZ(@Param("stationSet") Set<String> stationSet);
}

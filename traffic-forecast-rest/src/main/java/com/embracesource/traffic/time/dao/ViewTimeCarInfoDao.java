package com.embracesource.traffic.time.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.time.domain.ViewTimeCarInfoDo;
import com.embracesource.traffic.time.dto.ForecastStringDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface ViewTimeCarInfoDao extends BaseMapper<ViewTimeCarInfoDo> {

    public List<ViewTimeCarInfoDo> getTimeCareInfo(@Param("lj") String lj, @Param("ljName") String ljName, @Param("currentStation") String currentStation, @Param("time") String time);

    public List<ViewTimeCarInfoDo> getTimeCareInfoByPage(@Param("lj") String lj, @Param("ljName") String ljName, @Param("currentStation") String currentStation, @Param("pageSize") Integer pageSize, @Param("page") Integer page, @Param("time") String time);

    public ForecastStringDTO getforecastCar(@Param("hitId") String hitId);

    public List<ViewTimeCarInfoDo> getCarInfo(@Param("hitId") String hitId, @Param("time") String time);

    public String getStationName(@Param("station") String station);

    String getDOByForecastCar(@Param("hitId") String hitId, @Param("time") String time);

}

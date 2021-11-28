package com.embracesource.traffic.time.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.screen.dto.TempWSj2TopTenResponseDTO;
import com.embracesource.traffic.time.domain.TempWSj2DO;
import com.embracesource.traffic.time.dto.TempWSj2ResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TempWSj2Dao extends BaseMapper<TempWSj2DO> {

    List<Map<String, String>> selectTime(@Param("type")String type);

    List<Map<String, String>> selectLastWeek();

    List<TempWSj2ResponseDTO> selectAllMessage();

    String selectZdByCode(@Param("startStation") String startStation);

    //运输货品停留时间（TOP10）
    List<TempWSj2TopTenResponseDTO> getTempWSj2TopTen();
}

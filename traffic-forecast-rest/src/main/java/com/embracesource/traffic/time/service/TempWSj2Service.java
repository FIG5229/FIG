package com.embracesource.traffic.time.service;

import com.embracesource.traffic.screen.dto.TempWSj2TopTenResponseDTO;
import com.embracesource.traffic.time.domain.TempWSj2DO;
import com.embracesource.traffic.time.dto.TempWSj2ResponseDTO;

import java.util.List;
import java.util.Map;

public interface TempWSj2Service {

    List<Map<String, String>> selectQjTime();

    List<Map<String, String>> selectTlTime();

    List<Map<String, String>> selectLastWeek();

    List<TempWSj2ResponseDTO> selectAllMessage();

    //运输货品停留时间（TOP10）
    List<TempWSj2TopTenResponseDTO> getTempWSj2TopTen();
}

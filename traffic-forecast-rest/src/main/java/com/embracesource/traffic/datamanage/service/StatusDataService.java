package com.embracesource.traffic.datamanage.service;


import com.embracesource.traffic.datamanage.dto.StatusDataResponseDTO;

import java.util.List;

public interface StatusDataService {


    List<StatusDataResponseDTO> getStatusDataList(String wagonNumber, String currentStand, String startingStand, String endStand, String pageSize, String page,String date);

    Integer getStatusDataCount(String wagonNumber, String currentStand, String startingStand, String endStand, String pageSize,String date);
}

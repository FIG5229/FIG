package com.embracesource.traffic.time.service;

import com.embracesource.traffic.time.dto.*;

import java.util.List;
import java.util.Map;

public interface ViewTimeCarInfoService {

    //List<CarDTO> getTimeCareInfo(String lj,String ljName,String currentStation,String time);
    List<CarDTO> getTimeCareInfoByPage(String lj,String ljName,String currentStation,String pageSize,String page,String time);
    Integer getTimeCareInfoPageCount(String lj,String ljName,String currentStation,String pageSize,String time);
    ForcastCarInfoDTO getforecastCar(String hitId,String time);

    HeavyVehiclesResponseDTO getHeavyVehiclesByStation(String type, String station);
}

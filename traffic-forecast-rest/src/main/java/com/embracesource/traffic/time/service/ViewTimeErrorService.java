package com.embracesource.traffic.time.service;


import com.embracesource.traffic.time.dto.AccrateDTO;
import com.embracesource.traffic.time.dto.GetCountDTO;
import com.embracesource.traffic.time.dto.TimeAccrateDTO;

import java.util.Map;

public interface ViewTimeErrorService {

    //准确率和异常率
    AccrateDTO getAccrate(TimeAccrateDTO timeAccrateDTO);

    //准确率和异常率id
    Map<String,String> getId(String time1,String time2,String type);
    String getId(String time1,String time2,String flag,String type);

    GetCountDTO getCarCount();
}

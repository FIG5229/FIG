package com.embracesource.traffic.time.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.embracesource.traffic.screen.dto.TempWSj2TopTenResponseDTO;
import com.embracesource.traffic.time.dao.TempWSj2Dao;
import com.embracesource.traffic.time.domain.TempWSj2DO;
import com.embracesource.traffic.time.dto.TempWSj2ResponseDTO;
import com.embracesource.traffic.time.service.TempWSj2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@DS(value = "pgsql")
public class TempWSj2ServiceImpl implements TempWSj2Service {

    @Autowired
    TempWSj2Dao tempWSj2Dao;

    @Override
    public List<Map<String, String>> selectQjTime() {
        String type = "区间时间";
        return tempWSj2Dao.selectTime(type);
    }

    @Override
    public List<Map<String, String>> selectTlTime() {
        String type = "停留时间";
        return tempWSj2Dao.selectTime(type);
    }

    @Override
    public List<Map<String, String>> selectLastWeek() {
        return tempWSj2Dao.selectLastWeek();
    }

    @Override
    public List<TempWSj2ResponseDTO> selectAllMessage() {
        List<TempWSj2ResponseDTO> tempWSj2DOS = tempWSj2Dao.selectAllMessage();
        for (int i = 0; i < tempWSj2DOS.size(); i++){
            TempWSj2ResponseDTO tempWSj2ResponseDTO = tempWSj2DOS.get(i);

            String startStation = tempWSj2ResponseDTO.getStartStation();
            String endStation = tempWSj2ResponseDTO.getEndStation();
            String currentStation = tempWSj2ResponseDTO.getCurrentStation();
            String pathTimeDd = tempWSj2ResponseDTO.getPathTimeDd();
            String pathTimeCf = tempWSj2ResponseDTO.getPathTimeCf();


            if (!StringUtils.isEmpty(startStation)){
                String stStation = tempWSj2Dao.selectZdByCode(startStation);
                tempWSj2ResponseDTO.setStartStation(stStation);
            }
            if (!StringUtils.isEmpty(endStation)){
                String edStation = tempWSj2Dao.selectZdByCode(endStation);
                tempWSj2ResponseDTO.setEndStation(edStation);
            }
            if (!StringUtils.isEmpty(currentStation)){
                String cuStation = tempWSj2Dao.selectZdByCode(currentStation);
                tempWSj2ResponseDTO.setCurrentStation(cuStation);
            }
            if (!StringUtils.isEmpty(pathTimeDd)){

                tempWSj2ResponseDTO.setBwTime(subStringMessage(pathTimeDd));
            }else if (!StringUtils.isEmpty(pathTimeCf)){
                tempWSj2ResponseDTO.setBwTime(subStringMessage(pathTimeCf));
            }else{
                tempWSj2ResponseDTO.setBwTime(null);
            }
        }
        return tempWSj2DOS;
    }

    @Override
    public List<TempWSj2TopTenResponseDTO> getTempWSj2TopTen() {
        return tempWSj2Dao.getTempWSj2TopTen();
    }

    public String subStringMessage(String message){

        String year = message.substring(0, 2);
        String month = message.substring(2,4);
        String day = message.substring(4, 6);
        String hour = message.substring(6, 8);
        String minute = message.substring(8, 10);
        return "20" + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":00";

    }
}

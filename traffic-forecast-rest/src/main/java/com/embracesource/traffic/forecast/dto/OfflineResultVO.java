package com.embracesource.traffic.forecast.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ：wangshimin
 * @date ：Created in 2020-11-07 下午 07:03
 * @description：
 * @version:
 */
@Data
public class OfflineResultVO implements Serializable {
    private static final long serialVersionUID = 160097292706991629L;

    private String startStation;

    private String currentStation;

    private String endStation;

    private String nStation;

    private Long aNum;

    private String name;

    private String longitude;

    private String latitude;

    public OfflineResultVO(String startStation, String currentStation, String endStation, String nStation, Long aNum, String name, String latitude, String longitude) {
        this.startStation = startStation;
        this.currentStation = currentStation;
        this.endStation = endStation;
        this.nStation = nStation;
        this.aNum = aNum;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}

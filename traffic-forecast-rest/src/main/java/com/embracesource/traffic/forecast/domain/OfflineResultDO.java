package com.embracesource.traffic.forecast.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description
 * @Author wangshimin
 * @Date 2020-11-06 10:50:24
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfflineResultDO implements Serializable {

    private static final long serialVersionUID = 1600972927706991629L;

    private String startStation;
    private String currentStation;

    private String endStation;

    private String nStation;
    private Long aNum;

    private BigDecimal aDdMinutes;

    private BigDecimal aFdMinutes;

    private BigDecimal aTlMinutes;

    private String sfcl;

    private Long wNum;

    private BigDecimal wDdMinutes;

    private BigDecimal wFdMinutes;

    private BigDecimal wTlMinutes;

    private String id;

    public OfflineResultDO(String startStation, String currentStation, String endStation, String nStation, Long aNum) {
        this.startStation = startStation;
        this.currentStation = currentStation;
        this.endStation = endStation;
        this.nStation = nStation;
        this.aNum = aNum;
    }
}

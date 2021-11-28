package com.embracesource.traffic.time.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("offline_result")
public class ModelValidationDO {

    private String startStation;

    private String currentStation;

    private String endStation;

    private String nStation;

    private long aNum;

    private double aDdMinutes;

    private double aFdMinutes;

    private double aTlMinutes;

    private String sfcl;

    private long wNum;

    private double wDdMinutes;

    private double wFdMinutes;

    private double wTlMinutes;

}


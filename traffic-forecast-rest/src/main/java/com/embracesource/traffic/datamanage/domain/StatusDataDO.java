package com.embracesource.traffic.datamanage.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("clts_zyxcz")
public class StatusDataDO {

    private String tbrq;

    private String desStn;

    private String destStnName;

    private String carNo;

    private String curStn;

    private String curStnName;

    private String curAdm;

    private Timestamp rptDate;

    private String rptId;

    private String firstFjkCode;

    private Timestamp arrFirstFjkDate;

    private Timestamp arriveDate;

    private Timestamp updateTime;

    private long intervalMinutesEnd;

    private String leCode;

    private String destAdm;

    private String carKind;

    private String carType;

    private String cdyName;

    private String cdyCode;

    private String orgAdm;

    private String orgStn;

    private String orgStnName;

    private String trainId;

    private String trainNo;

    private String fhr;

    private String shr;

    private String yfFlag;

    private String trainType;

    private String carLength;

    private String carLightWgt;

    private String carCapWgt;

    private String note;

    private String wbNbr;

    private String gb;

    private String runLc;

    private String xdRefresh;

    private String inStation;

    private String nextSecStation;

    private Timestamp nextSecStaArrivaltime;

    private String xdCurStn;

    private String xdCurStnName;

    private String xdCurAdm;

    private Timestamp xdRptDate;

    private String blcStatus;

    private Timestamp reckTime;

    private Timestamp zcbgDate;

    private String zcbgFz;

    private String zcbgDz;

    private Timestamp partDate;

    private String operateYear;


}


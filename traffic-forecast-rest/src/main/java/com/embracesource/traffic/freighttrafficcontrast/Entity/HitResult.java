package com.embracesource.traffic.freighttrafficcontrast.Entity;


import com.baomidou.mybatisplus.annotation.TableName;

@TableName("hit_result")
public class HitResult {

    private String carNo;
    private String startStation;
    private String endStation;
    private String currentStation;
    private String reportType;
    private String pathTime;
    private String isHit;

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(String currentStation) {
        this.currentStation = currentStation;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getPathTime() {
        return pathTime;
    }

    public void setPathTime(String pathTime) {
        this.pathTime = pathTime;
    }

    public String getIsHit() {
        return isHit;
    }

    public void setIsHit(String isHit) {
        this.isHit = isHit;
    }
}

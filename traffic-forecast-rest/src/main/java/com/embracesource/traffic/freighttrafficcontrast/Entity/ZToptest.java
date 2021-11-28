package com.embracesource.traffic.freighttrafficcontrast.Entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("z_toptest1")
public class ZToptest {

    public String carNo;
    public String startStation;
    public String currentStation;
    public String endStation;
    public String nStation ;
    public String pathTimeDd;
    public String pathTimeCf;
    public String tlMinutes;
    public String aTlMinutes ;
    public String wTlMinutes ;
    public String error ;

    public String getPathTimeDd() {
        return pathTimeDd;
    }

    public void setPathTimeDd(String pathTimeDd) {
        this.pathTimeDd = pathTimeDd;
    }

    public String getPathTimeCf() {
        return pathTimeCf;
    }

    public void setPathTimeCf(String pathTimeCf) {
        this.pathTimeCf = pathTimeCf;
    }

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

    public String getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(String currentStation) {
        this.currentStation = currentStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getnStation() {
        return nStation;
    }

    public void setnStation(String nStation) {
        this.nStation = nStation;
    }

    public String getTlMinutes() {
        return tlMinutes;
    }

    public void setTlMinutes(String tlMinutes) {
        this.tlMinutes = tlMinutes;
    }

    public String getaTlMinutes() {
        return aTlMinutes;
    }

    public void setaTlMinutes(String aTlMinutes) {
        this.aTlMinutes = aTlMinutes;
    }

    public String getwTlMinutes() {
        return wTlMinutes;
    }

    public void setwTlMinutes(String wTlMinutes) {
        this.wTlMinutes = wTlMinutes;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

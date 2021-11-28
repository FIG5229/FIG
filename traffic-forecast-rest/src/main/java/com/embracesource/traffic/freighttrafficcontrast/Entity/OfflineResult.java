package com.embracesource.traffic.freighttrafficcontrast.Entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("offline_result")
public class OfflineResult {


    String startStation;
    String currentStation;
    String endStation;
    String nStation;

    int aNum;
    double aTlMinutes;
    int wNum;
    double wTlMinutes;


    public String getnStation() {
        return nStation;
    }

    public void setnStation(String nStation) {
        this.nStation = nStation;
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

    public int getaNum() {
        return aNum;
    }

    public void setaNum(int aNum) {
        this.aNum = aNum;
    }

    public double getaTlMinutes() {
        return aTlMinutes;
    }

    public void setaTlMinutes(double aTlMinutes) {
        this.aTlMinutes = aTlMinutes;
    }

    public int getwNum() {
        return wNum;
    }

    public void setwNum(int wNum) {
        this.wNum = wNum;
    }

    public double getwTlMinutes() {
        return wTlMinutes;
    }

    public void setwTlMinutes(double wTlMinutes) {
        this.wTlMinutes = wTlMinutes;
    }
}

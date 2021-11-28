package com.embracesource.traffic.freighttrafficcontrast.Entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("z_offline_result_no")
public class OfflineResult1 {


    String startStation;
    String currentStation;
    String endStation;
    String nStation;

    int aNum;
    double aTlMinutes;
    int mNum;
    double mTlMinutes;


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

    public int getmNum() {
        return mNum;
    }

    public void setmNum(int mNum) {
        this.mNum = mNum;
    }

    public double getmTlMinutes() {
        return mTlMinutes;
    }

    public void setmTlMinutes(double mTlMinutes) {
        this.mTlMinutes = mTlMinutes;
    }
}

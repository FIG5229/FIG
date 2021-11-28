package com.embracesource.traffic.freighttrafficcontrast.Entity;

public class StartAndEndStation {

    public StartAndEndStation(String startDM, String endDM, String start, String end) {
        this.startDM = startDM;
        this.endDM = endDM;
        this.start = start;
        this.end = end;
    }

    public StartAndEndStation() {
    }

    public String startDM;
    public String endDM;
    public String start;
    public String end;
    public String endTime;
    public String startTime;
    public int mileage;


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartDM() {
        return startDM;
    }

    public void setStartDM(String startDM) {
        this.startDM = startDM;
    }

    public String getEndDM() {
        return endDM;
    }

    public void setEndDM(String endDM) {
        this.endDM = endDM;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }


}

package com.embracesource.traffic.time.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "view_time_car_info")
public class ViewTimeCarInfoDo implements Serializable {
    @TableField(value = "hit_id")
    private String hitId;

    @TableField(value = "car_no")
    private String carNo;

    @TableField(value = "start_station")
    private String startStation;

    @TableField(value = "current_station")
    private String currentStation;

    @TableField(value = "end_station")
    private String endStation;

    @TableField(value = "report_time")
    private String reportTime;

    @TableField(value = "lj")
    private String lj;

    @TableField(value = "yj_cf_time")
    private String yjCfTime;

    @TableField(value = "yj_dd_time")
    private String yjDdTime;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "lybj")
    private String lybj;

    @TableField(value = "sjbj")
    private String sjbj;

    @TableField(value = "v_cur")
    private String vCur;

    @TableField(value = "v_id")
    private String vId;

    private String currentStationCode;

    //报文类型
    @TableField(value = "report_type")
    private String reportType;

    //周总标记
    @TableField(value = "is_week_forecast")
    private String isWeekForecast;

    @Override
    public String toString() {
        return "ViewTimeCarInfoDo{" +
                "hitId='" + hitId + '\'' +
                ", carNo='" + carNo + '\'' +
                ", startStation='" + startStation + '\'' +
                ", currentStation='" + currentStation + '\'' +
                ", endStation='" + endStation + '\'' +
                ", reportTime='" + reportTime + '\'' +
                ", lj='" + lj + '\'' +
                ", yjCfTime='" + yjCfTime + '\'' +
                ", yjDdTime='" + yjDdTime + '\'' +
                ", createTime=" + createTime +
                ", lybj='" + lybj + '\'' +
                ", sjbj='" + sjbj + '\'' +
                '}';
    }

    public ViewTimeCarInfoDo(String carNo) {
        this.carNo = carNo;
    }
}

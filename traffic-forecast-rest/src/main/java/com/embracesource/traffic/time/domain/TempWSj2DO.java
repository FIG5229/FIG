package com.embracesource.traffic.time.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value ="temp_w_sj_2" )
public class TempWSj2DO implements Serializable {


    @TableField(value = "gj_id" )
    private String gjId;

    @TableField(value = "car_no" )
    private String carNo;

    @TableField(value = "goods_type" )
    private String goodsType;

    //起始车站
    @TableField(value = "start_station" )
    private String startStation;

    //现在车站
    @TableField(value = "current_station" )
    private String currentStation;

    //终点站
    @TableField(value = "end_station" )
    private String endStation;

    @TableField(value = "path_time_dd" )
    private String pathTimeDd;

    @TableField(value = "path_time_cf" )
    private String pathTimeCf;

    @TableField(value = "n_station" )
    private String nStation;

    @TableField(value = "n_path_time" )
    private String nPathTime;

    @TableField(value = "dd_sj" )
    private String ddSj;

    @TableField(value = "fd_sj" )
    private String fdSj;

    @TableField(value = "tl_sj" )
    private String tlSj;

    @TableField(value = "dd_minutes" )
    private String ddMinutes;

    @TableField(value = "fd_minutes" )
    private String fdMinutes;

    @TableField(value = "tl_minutes" )
    private String tlMinutes;









}

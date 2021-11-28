package com.embracesource.traffic.screen.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description:
 * @Date: Created inMon Jan 25 15:21:24 CST 2021
 * @Author: wangshimin
 * @Modified By:
 */
@Data
@TableName(value = "car_result_info_history")
public class CarResultInfoHistory implements Serializable {

    private static final long serialVersionUID = 1003409050522381443L;

    @TableField(value = "item_id")
    private String itemId;

    @TableField(value = "car_no")
    private String carNo;

    @TableField(value = "report_value")
    private String reportName;

    @TableField(value = "goods_type")
    private String goodsType;

    @TableField(value = "car_type")
    private String carType;

    @TableField(value = "start_station")
    private String startStation;

    @TableField(value = "current_station")
    private String currentStation;

    @TableField(value = "end_station")
    private String endStation;

    @TableField(value = "report_type")
    private String reportType;

    @TableField(value = "lecode")
    private String lecode;

    @TableField(value = "path_time")
    private String pathTime;

    @TableField(value = "report_time")
    private Date reportTime;

    @TableField(value = "store_time")
    private String storeTime;

    @TableField(value = "tanker")
    private String tanker;

    @TableField(value = "nodes_info")
    private String nodesInfo;

    @TableField(value = "train_no")
    private String trainNo;

    @TableField(value = "train_id")
    private String trainId;

    @TableField(value = "train_direction")
    private String trainDirection;

    @TableField(value = "ztbz")
    private String ztbz;

    @TableField(value = "hwhz")
    private String hwhz;

    @TableField(value = "shr")
    private String shr;

    @TableField(value = "fhr")
    private String fhr;

    @TableField(value = "slh")
    private String slh;

    @TableField(value = "car_kind")
    private String carKind;

    @TableField(value = "cache_status")
    private String cacheStatus;

    @TableField(value = "hpid")
    private String hpid;

    @TableField(value = "tj_riqi")
    private String tjRiqi;

    @TableField(value = "xh")
    private String xh;

    @TableField(value = "result_source")
    private String resultSource;

    @TableField(value = "create_time")
    private String createTime;

    @TableField(value = "jtzm")
    private String jtzm;

    @TableField(value = "operate_year")
    private String operateYear;

    public int getHour() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(reportTime);
        return calendar.get(Calendar.HOUR);
    }
}

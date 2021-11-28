package com.embracesource.traffic.screen.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Date: Created inMon Jan 25 15:21:24 CST 2021
 * @Author: wangshimin
 * @Modified By:
 */
@Data
@TableName(value = "hit_result")
public class HitResult implements Serializable {

    private static final long serialVersionUID = 4796224603417868247L;

    @TableField(value = "hit_id")
    private String hitId;

    @TableField(value = "start_station")
    private String startStation;

    @TableField(value = "end_station")
    private String endStation;

    @TableField(value = "goods_type")
    private String goodsType;

    @TableField(value = "car_no")
    private String carNo;

    @TableField(value = "current_station")
    private String currentStation;

    @TableField(value = "report_type")
    private String reportType;

    @TableField(value = "path_time")
    private String pathTime;

    @TableField(value = "report_time")
    private String reportTime;

    /*1是正确的，0是错误的*/
    @TableField(value = "is_hit")
    private String isHit;

    @TableField(value = "leave_date")
    private String leaveDate;

    @TableField(value = "arrive_date")
    private String arriveDate;

    @TableField(value = "error")
    private String error;

    @TableField(value = "is_offset")
    private String isOffset;

    @TableField(value = "create_time")
    private String createTime;

}

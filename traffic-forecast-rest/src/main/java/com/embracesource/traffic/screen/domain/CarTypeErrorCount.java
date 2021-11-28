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
@TableName(value = "car_type_error_count")
public class CarTypeErrorCount implements Serializable {

    private static final long serialVersionUID = 2151129682090322689L;

    @TableField(value = "item_id")
    private String itemId;

    @TableField(value = "train_id")
    private String trainId;

    @TableField(value = "car_no")
    private String carNo;

    @TableField(value = "car_type")
    private String carType;

    @TableField(value = "goods_type")
    private String goodsType;

    @TableField(value = "start_station")
    private String startStation;

    @TableField(value = "current_station")
    private String currentStation;

    @TableField(value = "end_station")
    private String endStation;

    @TableField(value = "path_time")
    private String pathTime;

    @TableField(value = "report_time")
    private String reportTime;

    @TableField(value = "report_type")
    private String reportType;

    @TableField(value = "lecode")
    private String lecode;

    @TableField(value = "jtz")
    private String jtz;

    @TableField(value = "create_time")
    private String createTime;

    @TableField(value = "rule")
    private String rule;

}

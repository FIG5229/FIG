package com.embracesource.traffic.predict.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: daniel.liu
 * @Description:
 * @Date: create in 2021/5/26 14:08
 */
@Data
@TableName(value = "a_hit_distinct_2")
public class AHitDistinct2 implements Serializable {

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

    @TableField(value = "is_hit")
    private String isHit;


}

package com.embracesource.traffic.screen.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: daniel.liu
 * @Description:
 * @Date: create in 2021/5/21 10:32
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "预测数据返回实体")
public class PredictCarResultDTO implements Serializable {

    @ApiModelProperty(value = "主键")
    private String itemId;

    @ApiModelProperty(value = "车辆号，查询需要")
    private String carNo;

    @ApiModelProperty(value = "上一站，查询需要")
    private String startStation;

    @ApiModelProperty(value = "当前站，查询需要")
    private String currentStation;

    @ApiModelProperty(value = "下一站，查询需要")
    private String endStation;

    @ApiModelProperty(value = "货物类型")
    private String goodsType;

    @ApiModelProperty(value = "报文时间")
    private String reportTime;

    @ApiModelProperty(value = "报文类型")
    private String reportType;

    @ApiModelProperty(value = "列车号")
    private String trainNo;

    @ApiModelProperty(value = "0轻车  1重车")
    private String lecode;
}

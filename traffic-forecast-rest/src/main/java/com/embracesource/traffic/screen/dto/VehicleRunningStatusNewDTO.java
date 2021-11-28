package com.embracesource.traffic.screen.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：kevin.jia
 * @date ：Created in 2021-06-23 下午 04:29
 * @description：
 * @version:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "车辆运行情况实体")
public class VehicleRunningStatusNewDTO {
    @ApiModelProperty(value = "车次号")
    private String carNo;

    @ApiModelProperty(value = "当前站点")
    private String currentName;

    @ApiModelProperty(value = "预计到达时间")
    private String estimatedTimeArrival;

    @ApiModelProperty(value = "预计出发时间")
    private String expectedDepartureTime;

    @ApiModelProperty(value = "起始站")
    private String startStation;

    @ApiModelProperty(value = "终点站")
    private String endStation;

    @ApiModelProperty(value = "货物类型")
    private String goodsType;

    @ApiModelProperty(value = "报文时间")
    private String reportTime;

    @ApiModelProperty(value = "运行状态")
    private String runStatus;

    @ApiModelProperty(value = "运行比率,(百分比)(运行时间/总时长)*100")
    private double runRatio;
    @ApiModelProperty(value = "运行时长")
    private Long runTime;
    @ApiModelProperty(value = "总运行时长")
    private Long TotalTime;
}


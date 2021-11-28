package com.embracesource.traffic.screen.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-27 上午 10:29
 * @description：
 * @version:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "车辆运行情况返回实体")
public class VehicleRunningStatusDTO {
    @ApiModelProperty(value = "车次号,查询需要")
    private String carNo;

    @ApiModelProperty(value = "当前站点名称")
    private String currentName;

    @ApiModelProperty(value = "预计到达时间")
    private String estimatedTimeArrival;

    @ApiModelProperty(value = "预计出发时间")
    private String expectedDepartureTime;

    @ApiModelProperty(value = "起始站,查询需要")
    private String startStation;

    @ApiModelProperty(value = "终点站,查询需要")
    private String endStation;

    @ApiModelProperty(value = "货物类型,查询需要")
    private String goodsType;
}


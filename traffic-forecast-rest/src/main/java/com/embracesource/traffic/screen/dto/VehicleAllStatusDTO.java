package com.embracesource.traffic.screen.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-27 下午 04:42
 * @description：
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "查询列车运行状态入参")
public class VehicleAllStatusDTO {
    @NotNull
    @ApiModelProperty(value = "车辆号")
    private String carNo;

    @NotNull
    @ApiModelProperty(value = "起始站")
    private String startStation;

    @NotNull
    @ApiModelProperty(value = "终点站")
    private String endStation;

    @NotNull
    @ApiModelProperty(value = "货物类型")
    private String goodsType;
}

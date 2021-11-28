package com.embracesource.traffic.forecast.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ：wangshimin
 * @date ：Created in 2020-11-05 下午 04:50
 * @description：地图返回数据
 * @version:
 */
@Data
@ApiModel(description = "地图返回数据")
public class GISResultDTO {

    @ApiModelProperty(value = "起始站点")
    private CityDTO start_station;

    @ApiModelProperty(value = "当前站点")
    private CityDTO current_station;

    @ApiModelProperty(value = "终点站点")
    private CityDTO end_station;

    @ApiModelProperty(value = "货物类型")
    private String car_kind;

    @ApiModelProperty(value = "实际路线")
    private List<RouteDTO> actual_route;

    @ApiModelProperty(value = "预测路线")
    private List<String> predict_route;

}

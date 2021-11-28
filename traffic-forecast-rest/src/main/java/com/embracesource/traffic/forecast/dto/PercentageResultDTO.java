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
@ApiModel(description = "百分比返回数据")
public class PercentageResultDTO {

    @ApiModelProperty(value = "当前站点")
    private CityDTO current_station;

    @ApiModelProperty(value = "路线")
    private List<CityDTO> routes;

}

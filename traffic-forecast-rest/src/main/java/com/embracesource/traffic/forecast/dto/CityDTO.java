package com.embracesource.traffic.forecast.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：wangshimin
 * @date ：Created in 2020-11-05 下午 04:51
 * @description：城市数据
 * @version:
 */
@Data
@ApiModel(description = "城市数据")
public class CityDTO implements Serializable {

    @ApiModelProperty(value = "城市名称")
    private String city;

    @ApiModelProperty(value = "城市code")
    private String cityCode;

    @ApiModelProperty(value = "精度")
    private String lon;

    @ApiModelProperty(value = "纬度")
    private String lat;

    @ApiModelProperty(value = "预测百分比")
    private Double percentage;

}

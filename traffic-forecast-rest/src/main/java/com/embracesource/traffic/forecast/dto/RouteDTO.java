package com.embracesource.traffic.forecast.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ：wangshimin
 * @date ：Created in 2020-11-06 上午 10:09
 * @description：实际线路
 * @version:
 */
@Data
@ApiModel(description = "实际线路返回实体")
public class RouteDTO {

    @ApiModelProperty(value = "路线")
    private List<CityDTO> routes;

    @ApiModelProperty(value = "是否是最优线路")
    private boolean isOptimal;
}

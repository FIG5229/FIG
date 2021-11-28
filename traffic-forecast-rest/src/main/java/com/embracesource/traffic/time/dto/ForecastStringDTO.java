package com.embracesource.traffic.time.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * .
 * User: huqiaomei
 * Date: 2021/3/29 10:14
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "预测车辆串")
public class ForecastStringDTO {
    @ApiModelProperty(value = "预测车辆串")
    private String nodesInfo;

    @ApiModelProperty(value = "类型")
    private String resultSource;
}

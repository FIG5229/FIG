package com.embracesource.traffic.forecast.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author ：wangshimin
 * @date ：Created in 2020-11-08 下午 01:42
 * @description：
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "查询百分比数据")
public class SelectPercentageDTO {

    private static final long serialVersionUID = 160097298506991629L;

    /**
     * 起始站
     */
    @NotBlank(message = "起始站英文名不能为空")
    @ApiModelProperty(value = "起始站英文名", required = true)
    private String startStationEn;

    @NotBlank(message = "当前站英文名不能为空")
    @ApiModelProperty(value = "起始站英文名", required = true)
    private String currentStation;

    @NotBlank(message = "终点站英文名不能为空")
    @ApiModelProperty(value = "起始站英文名", required = true)
    private String endStation;
}

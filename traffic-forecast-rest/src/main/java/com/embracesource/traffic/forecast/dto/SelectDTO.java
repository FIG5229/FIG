package com.embracesource.traffic.forecast.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @name: SelectDTO
 * @Author: wangshimin
 * @Date: 2020/9/25 0025  上午 9:56
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "查询数据")
public class SelectDTO {

    /**
     * 起始站
     */
    @NotBlank(message = "起始站数据不能为空")
    @ApiModelProperty(value = "起始站", example = "北京", required = true)
    private String start_station;

    /**
     * 当前站
     */
    @ApiModelProperty(value = "当前站", example = "济南", required = false)
    private String current_station;

    /**
     * 终点站
     */
    @NotBlank(message = "结束站数据不能为空")
    @ApiModelProperty(value = "结束站", example = "上海", required = true)
    private String end_station;

}

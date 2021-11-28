package com.embracesource.traffic.screen.dto;

import com.embracesource.traffic.forecast.dto.GISResultDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-25 下午 05:03
 * @description：预测次数统计实体
 * @version:
 */
@Data
@ApiModel(description = "预测次数统计实体")
public class TrainsRunDayDataDTO {

    @ApiModelProperty(value = "每日运行车次")
    private Long nowDayAmount;

    @ApiModelProperty(value = "地图数据")
    private List<GISResultDTO> GisCity;

}

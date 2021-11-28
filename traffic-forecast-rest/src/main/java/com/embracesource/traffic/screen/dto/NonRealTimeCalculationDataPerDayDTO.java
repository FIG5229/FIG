package com.embracesource.traffic.screen.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-27 下午 05:32
 * @description：
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "非实时计算返回实体 数据单位 天")
public class NonRealTimeCalculationDataPerDayDTO {

    @ApiModelProperty(value = "异常统计- 天")
    private Float abnormalPredictStatisticsPeray;

    @ApiModelProperty(value = "异常数据统计量")
    private Map<String, Long> abnormalPredictDataStatistics;
}

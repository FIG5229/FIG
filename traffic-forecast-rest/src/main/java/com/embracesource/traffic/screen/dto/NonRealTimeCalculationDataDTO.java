package com.embracesource.traffic.screen.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-27 下午 05:15
 * @description：
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "非实时计算返回实体")
public class NonRealTimeCalculationDataDTO {

    @ApiModelProperty(value = "路径测试")
    private Map<String, Float> pathTests;

    @ApiModelProperty(value = "路局管内车流")
    private Map<String, Long> railwayAdministrationsTubeTraffic;

    @ApiModelProperty(value = "路局准确率")
    private Map<String, Float> railwayAdministrationsAccuracy;

    @ApiModelProperty(value = "路局命中率")
    private Map<String, Float> railwayAdministrationsToPredictShoot;

    @ApiModelProperty(value = "输入数据总量/错误量")
    private Float totalInputErrorData;

    @ApiModelProperty(value = "异常预测站点分布")
    private Map<String, Long> abnormalDistributionPredictionSite;

    @ApiModelProperty(value = "异常站点稽核统计")
    private Map<String, Float> auditingStatisticalAnomalyData;

    @ApiModelProperty(value = "运输货品停留时间")
    private Map<String, Long> residenceTimeTransportGoods;

    @ApiModelProperty(value = "站点通行能力")
    private Map<String, Float> sitePeersAbility;

    @ApiModelProperty(value = "站点异常统计")
    private Map<String, Float> abnormalSiteStatistics;

    @ApiModelProperty(value = "总量统计")
    private Map<String, Object> totalStatistics;
}

package com.embracesource.traffic.screen.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @program: traffic-forecast
 * @description: 大屏展示第二页接口结果集
 * @author: kevin.jia
 * @create: 2021-05-24 17:57
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "大屏展示第二页左半部分接口结果集")
public class BigScreenTwoLeftResponseDTO {
    @ApiModelProperty(value = "运输货品停留时间（TOP10）")
    private List<TempWSj2TopTenResponseDTO> tempWSj2TopTenList;

//    @ApiModelProperty(value = "异常预测数据量统计")
//    private List<UnusualForecastDataNumResponseDTO> unusualForecastDataNumList;

    @ApiModelProperty(value = "站点通行能力（TOP10）")
    private List<SitTrafficTopTenResponseDTO> sitTrafficTopTenList;

    @ApiModelProperty(value = "站点异常统计")
    private List<SiteAbnormalStatisticsResponseDTO> siteAbnormalStatisticsList;
}

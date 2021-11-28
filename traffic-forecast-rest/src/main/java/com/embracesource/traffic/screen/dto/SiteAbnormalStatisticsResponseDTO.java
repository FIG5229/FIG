package com.embracesource.traffic.screen.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: traffic-forecast
 * @description: 站点异常统计返回实体类
 * @author: kevin.jia
 * @create: 2021-05-24 17:49
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value ="站点异常统计")
public class SiteAbnormalStatisticsResponseDTO {

    @ApiModelProperty(value = "故障类型")
    private String gzTypeModel;

    @ApiModelProperty(value = "单个故障数量")
    private Integer num;

    @ApiModelProperty(value = "故障总数量")
    private Integer totalNum;

    @ApiModelProperty(value = "百分比")
    private String percent;
}

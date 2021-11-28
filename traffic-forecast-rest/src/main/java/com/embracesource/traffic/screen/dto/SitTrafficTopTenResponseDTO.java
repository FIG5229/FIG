package com.embracesource.traffic.screen.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: traffic-forecast
 * @description: 站点通行能力TOP10返回实体
 * @author: kevin.jia
 * @create: 2021-05-24 16:28
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value ="站点通行能力（TOP10）" )
public class SitTrafficTopTenResponseDTO {

    @ApiModelProperty(value = "站点")
    private String currentStation;

    @ApiModelProperty(value = "通行数量")
    private Integer num;

    @ApiModelProperty(value = "本日总通行数量")
    private Integer totalNum;

    @ApiModelProperty(value = "百分比")
    private String percent;
}

package com.embracesource.traffic.datamanage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: traffic-forecast
 * @description: 命中数据返回参数类
 * @author: kevin.jia
 * @create: 2021-05-24 09:27
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("命中数据返回参数实体")
public class HitResultResponseDTO {

    @ApiModelProperty("主键")
    private String hitId;
    @ApiModelProperty("车号")
    private String carNo;
    @ApiModelProperty("起始站")
    private String startStation;
    @ApiModelProperty("当前站")
    private String currentStation;
    @ApiModelProperty("终点站")
    private String endStation;
    @ApiModelProperty("货物类型")
    private String goodsType;
    @ApiModelProperty("报文时间")
    private String reportTime;
    @ApiModelProperty("报文类型")
    private String reportType;
    @ApiModelProperty("是否命中")
    private String isHit;


}

package com.embracesource.traffic.datamanage.dto;

import com.embracesource.traffic.base.pageHelper.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: traffic-forecast
 * @description: 命中数据请求参数类
 * @author: kevin.jia
 * @create: 2021-05-24 09:27
 **/


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("命中数据请求参数实体")
public class HitResultRequestDTO extends PageRequest {

    @ApiModelProperty(value = "车号")
    private String carNo;

    @ApiModelProperty(value = "当前站")
    private String currentStation;

    @ApiModelProperty(value = "始发站")
    private String startStation;

    @ApiModelProperty(value = "终到站")
    private String endStation;
}

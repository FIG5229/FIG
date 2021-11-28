package com.embracesource.traffic.time.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：tom.fu
 * @date ：Created in 2021/5/20
 * @description：
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("模型分析规则校验命中率未命中返回数据实体")
public class HitRateNotHitResponseDTO {


    @ApiModelProperty(value = "车次")
    private String carNo;

    @ApiModelProperty(value = "起始站")
    private String startStation;

    @ApiModelProperty(value = "终点站")
    private String endStation;




}

package com.embracesource.traffic.time.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：tom.fu
 * @date ：Created in 2021/5/20
 * @description：模型验证-规则检验-时间偏差加大区间柱状图
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("模型分析规则校验区间柱状图出参实体")
public class SectionResponseDTO {

    @ApiModelProperty(value = "当前站")
    private String startStation;

    @ApiModelProperty(value = "终点站")
    private String endStation;

    @ApiModelProperty(value = "偏差")
    private String round;


}

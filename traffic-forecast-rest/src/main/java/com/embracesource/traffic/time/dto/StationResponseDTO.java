package com.embracesource.traffic.time.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：tom.fu
 * @date ：Created in 2021/5/25
 * @description：模型验证-规则检验-时间偏差加大车站柱状图
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("模型分析规则校验车站柱状图出参实体")
public class StationResponseDTO {


    @ApiModelProperty(value = "当前站")
    private String currentStation;

    @ApiModelProperty(value = "偏差")
    private String round;


}

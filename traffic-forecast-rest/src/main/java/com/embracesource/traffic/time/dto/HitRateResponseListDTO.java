package com.embracesource.traffic.time.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ：tom.fu
 * @date ：Created in 2021/5/20
 * @description：模型分析规则校验命中率
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("模型分析规则校验命中率出参实体")
public class HitRateResponseListDTO {

    /**
     * 站点--查到的上一站
     */
    @ApiModelProperty(value = "站点")
    private String station;

    @ApiModelProperty(value = "命中率")
    private Integer hitRate;


}

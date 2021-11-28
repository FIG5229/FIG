package com.embracesource.traffic.time.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：tom.fu
 * @date ：Created in 2021/5/20
 * @description：模型分析规则修正下面列表出参dto
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("模型分析规则修正出参实体")
public class ModelValidationResponseDTO {

    @ApiModelProperty(value = "当前站")
    private String startStation;

    @ApiModelProperty(value = "周次数")
    private String wNum;

    @ApiModelProperty(value = "总次数")
    private String aNum;

    @ApiModelProperty(value = "人为")
    private String artificial = "1";        //暂时没有人为干预 后期有需要再改动 值暂时设置为  1

    @ApiModelProperty(value = "周发到时间")
    private String wFdMinutes;

    @ApiModelProperty(value = "周到到时间")
    private String wDdMinutes;

    @ApiModelProperty(value = "总发到时间")
    private String aFdMinutes;

    @ApiModelProperty(value = "总到到时间")
    private String aDdMinutes;

    @ApiModelProperty(value = "周停留时间")
    private String wTlMinutes;

    @ApiModelProperty(value = "总停留时间")
    private String aTlMinutes;

}

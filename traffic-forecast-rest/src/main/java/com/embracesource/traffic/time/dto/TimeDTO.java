package com.embracesource.traffic.time.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "路局对应比率")
public class TimeDTO {
    @ApiModelProperty(value = "路局")
    private String lj;

    @ApiModelProperty(value = "路局名称")
    private String ljName;

    @ApiModelProperty(value = "比率")
    private String accrate;






}

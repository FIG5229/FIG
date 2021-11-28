package com.embracesource.traffic.time.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "准确率和异常率")
public class AccrateDTO {
    @ApiModelProperty(value = "准确率")
    private String accrateA;

    @ApiModelProperty(value = "异常率")
    private String accrateB;
}

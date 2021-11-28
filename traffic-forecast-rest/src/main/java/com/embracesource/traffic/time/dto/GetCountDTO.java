package com.embracesource.traffic.time.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "获取日车辆数、日出发数、日到达数")
public class GetCountDTO {

    @ApiModelProperty(value = "日车辆数")
    private Long carCount;

    @ApiModelProperty(value = "日出发数")
    private Long leaveCount;

    @ApiModelProperty(value = "日到达数")
    private Long arriveCount;
}

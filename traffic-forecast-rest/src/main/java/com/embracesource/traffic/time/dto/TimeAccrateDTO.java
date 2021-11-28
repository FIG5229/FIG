package com.embracesource.traffic.time.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "某时某时下编组站或卸车站的比率")
public class TimeAccrateDTO {

    @ApiModelProperty(value = "大时间")
    private String time1;

    @ApiModelProperty(value = "小时间")
    private String time2;

    @ApiModelProperty(value = "1表示卸车站，0表示编组站")
    private String type;
}

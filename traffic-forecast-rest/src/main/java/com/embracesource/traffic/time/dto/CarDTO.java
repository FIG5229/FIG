package com.embracesource.traffic.time.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "路局车辆详细信息")
public class CarDTO {
    @ApiModelProperty(value = "需要隐藏的字段")
    private String hitId;

    @ApiModelProperty(value = "几时，需要隐藏的字段")
    private String time;

    @ApiModelProperty(value = "车号")
    private String carNo;

    @ApiModelProperty(value = "始发站")
    private String startStation;

    @ApiModelProperty(value = "当前站")
    private String currentStation;

    @ApiModelProperty(value = "终点站")
    private String endStation;

    @ApiModelProperty(value = "预计出发时间")
    private String yjCfTime;

    @ApiModelProperty(value = "预计到达时间")
    private String yjDdTime;

    @ApiModelProperty(value = "报文时间")
    private String reportTime; //实际到达时间

    @ApiModelProperty(value = "报文类型")
    private String reportType;

    @ApiModelProperty(value = "周总标记：1表示周，0表示总")
    private String isWeekForecast;

    @Override
    public String toString() {
        return "CarDTO{" +
                "startStation='" + startStation + '\'' +
                ", currentStation='" + currentStation + '\'' +
                ", endStation='" + endStation + '\'' +
                '}';
    }
}



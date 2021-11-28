package com.embracesource.traffic.time.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value ="时间态势返回值" )
public class TempWSj2ResponseDTO implements Serializable {

    @ApiModelProperty(value = "车号")
    private String carNo;

    @ApiModelProperty(value = "起始站")
    private String startStation;

    //现在车站
    @ApiModelProperty(value = "当前站")
    private String currentStation;

    //终点站
    @ApiModelProperty(value = "终点站")
    private String endStation;

    @ApiModelProperty(value = "到达时间")
    private String pathTimeDd;

    @ApiModelProperty(value = "出发时间")
    private String pathTimeCf;

    @ApiModelProperty(value = "站点")
    private String ddMinutes;

    @ApiModelProperty(value = "报文时间")
    private String bwTime;

    @ApiModelProperty(value = "区间时间")
    private String fdMinutes;

    @ApiModelProperty(value = "停留时间")
    private String tlMinutes;

}

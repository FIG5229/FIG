package com.embracesource.traffic.datamanage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：tom.fu
 * @date ：Created in 2021/5/20
 * @description：状态数据下面列表出参dto
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("数据处理状态数据出参实体")
public class StatusDataResponseDTO {

    @ApiModelProperty(value = "提报日期")
    private String tbrq;

    @ApiModelProperty(value = "车号")
    private String carNo;

    @ApiModelProperty(value = "起始站")
    private String orgStn;

    @ApiModelProperty(value = "当前站")
    private String curStn;

    @ApiModelProperty(value = "终点站")
    private String destStn;

    @ApiModelProperty(value = "货物类型")
    private String cdyCode;

    @ApiModelProperty(value = "报文时间")
    private String rptDte;

    @ApiModelProperty(value = "报文类型")
    private String rptId;

    @ApiModelProperty(value = "列车id")
    private String trainNo;

    @ApiModelProperty(value = "是否重车")
    private String leCode;




}

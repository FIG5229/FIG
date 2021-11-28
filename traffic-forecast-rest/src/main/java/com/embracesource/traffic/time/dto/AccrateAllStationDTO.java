package com.embracesource.traffic.time.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * .
 * User: huqiaomei
 * Date: 2021/3/19 15:24
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "全站全路局准确率和异常率、经纬度")
public class AccrateAllStationDTO {

    @ApiModelProperty(value = "站码")
    private String station;

    @ApiModelProperty(value = "站名")
    private String stationName;

    @ApiModelProperty(value = "路局")
    private String lj;

    @ApiModelProperty(value = "路局名称")
    private String ljName;

    @ApiModelProperty(value = "比率")
    private String accrate;

    //根据站拿到精度、纬度
    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

}

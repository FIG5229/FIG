package com.embracesource.traffic.time.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * .
 * User: huqiaomei
 * Date: 2021/3/19 16:01
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "时间和站类型")
public class AllTimeDTO {
    @ApiModelProperty(value = "大时间")
    private String time1;

    @ApiModelProperty(value = "小时间")
    private String time2;

    @ApiModelProperty(value = "1表示准确率，0表示异常率")
    private String flag;

    @ApiModelProperty(value = "1表示卸车站，0表示编组站")
    private String type;
}

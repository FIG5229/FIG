package com.embracesource.traffic.time.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "某路局车辆详细信息-分页")
public class CarPageDTO {
    @ApiModelProperty(value = "路局")
    private String lj;

    @ApiModelProperty(value = "路局名称")
    private String ljName;

    @ApiModelProperty(value = "当前站")
    private String currentStation;

    @ApiModelProperty(value = "每页显示条数")
    private String pageSize;

    @ApiModelProperty(value = "第几页")
    private String page;

    @ApiModelProperty(value = "大时间")
    private String time1;

    @ApiModelProperty(value = "小时间")
    private String time2;

    @ApiModelProperty(value = "1表示准确率，0表示异常率")
    private String flag;

    @ApiModelProperty(value = "1表示卸车站，0表示编组站")
    private String type;

}

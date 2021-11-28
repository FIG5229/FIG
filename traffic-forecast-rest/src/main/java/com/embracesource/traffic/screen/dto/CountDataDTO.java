package com.embracesource.traffic.screen.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: daniel.liu
 * @Description:
 * @Date: create in 2021/5/24 15:35
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "数据处理量返回实体")
public class CountDataDTO implements Serializable {

    @ApiModelProperty(value = "LCDD")
    private long lcdd;
    @ApiModelProperty(value = "LCCF")
    private long lccf;
    @ApiModelProperty(value = "CHSB")
    private long chsb;

    @ApiModelProperty(value = "时间")
    private String label;

    @ApiModelProperty(value = "排序使用")
    private Date date;


}

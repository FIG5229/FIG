package com.embracesource.traffic.screen.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: daniel.liu
 * @Description:
 * @Date: create in 2021/5/24 15:35
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "统计返回实体")
public class CountInfoDTO implements Serializable {

    @ApiModelProperty(value = "标签/类型/x轴/...")
    private String label;

    @ApiModelProperty(value = "数量")
    private long count;

}

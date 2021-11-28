package com.embracesource.traffic.screen.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author: daniel.liu
 * @Description:
 * @Date: create in 2021/5/24 13:48
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "大屏右侧返回实体")
public class RightInputDataAndExcept implements Serializable {

    @ApiModelProperty(value = "输入数据总量")
    private long totalInputData;

    @ApiModelProperty(value = "输入数据错误量")
    private long errorInputData;

    @ApiModelProperty(value = "异常预测统计正确量")
    private long predictCorrectNum;

    @ApiModelProperty(value = "异常预测统计错误量")
    private long preDictErrorNum;
}

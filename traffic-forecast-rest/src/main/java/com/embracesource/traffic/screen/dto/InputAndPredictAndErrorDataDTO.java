package com.embracesource.traffic.screen.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-27 上午 10:21
 * @description：
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "实时模型输入数量+实时模型预测数量+实时模型异常数量返回实体")
public class InputAndPredictAndErrorDataDTO {

    @ApiModelProperty(value = "实时模型输入数量")
    private List<Map<String,Object>> realTimeModelInputNumber;

    @ApiModelProperty(value = "实时模型预测数量")
    private List<Map<String,Object>> realTimeModelPredicts;

    @ApiModelProperty(value = "实时模型异常数量")
    private List<Map<String,Object>> realTimeModelErrorNumber;

}

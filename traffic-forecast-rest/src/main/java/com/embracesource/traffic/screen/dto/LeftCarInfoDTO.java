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
@ApiModel(value = "大屏左侧返回实体")
public class LeftCarInfoDTO implements Serializable {

    @ApiModelProperty(value = "路径测试-命中率")
    private Object hitRate;

    @ApiModelProperty(value = "路径测试-准确率")
    private Object accuracy;

    @ApiModelProperty(value = "路局管内车流")
    private List<Map<String,Object>> traffic;


    @ApiModelProperty(value = "路局预测命中率")
    private List<Map<String, Object>> hitRateRailway;

    @ApiModelProperty(value = "路局预测准确率")
    private List<Map<String,Object>> accuracyRailway;
}

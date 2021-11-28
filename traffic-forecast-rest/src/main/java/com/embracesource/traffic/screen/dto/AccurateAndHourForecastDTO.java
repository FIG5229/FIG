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
 * @date ：Created in 2021-01-27 上午 10:09
 * @description：
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "准确率统计-月（最后月份）+ 小时统计预测数（最后小时数据）返回实体")
public class AccurateAndHourForecastDTO {

    @ApiModelProperty(value = "准确率统计-月")
    private List<Map<String ,Object>> accountList;


    @ApiModelProperty(value = "小时统计预测数")
    private List<CountInfoDTO> hourForecastList;

}

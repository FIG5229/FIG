package com.embracesource.traffic.screen.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: traffic-forecast
 * @description: 异常预测数据量返回实体
 * @author: kevin.jia
 * @create: 2021-05-24 13:37
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value ="异常预测数据量统计" )
public class UnusualForecastDataNumResponseDTO {
    @ApiModelProperty("月份")
    private String month;

    @ApiModelProperty("数量")
    private Integer num;
}

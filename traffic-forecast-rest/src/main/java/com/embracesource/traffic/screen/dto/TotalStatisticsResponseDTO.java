package com.embracesource.traffic.screen.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: traffic-forecast
 * @description: 总量统计回实体类
 * @author: kevin.jia
 * @create: 2021-05-24 17:49
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value ="总量统计")
public class TotalStatisticsResponseDTO {

    @ApiModelProperty(value = "名称")
    private String title;

    @ApiModelProperty(value = "数据")
    private String content;

}

package com.embracesource.traffic.screen.dto;

import com.embracesource.traffic.base.pageHelper.PageInfo;
import com.embracesource.traffic.base.pageHelper.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: daniel.liu
 * @Description:
 * @Date: create in 2021/5/21 10:32
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "预测数据返回实体")
public class PredictCarResultRequestDTO extends PageRequest {

    @ApiModelProperty(value = "车辆号，查询需要")
    private String carNo;

    @ApiModelProperty(value = "上一站，查询需要")
    private String startStation;

    @ApiModelProperty(value = "当前站，查询需要")
    private String currentStation;

    @ApiModelProperty(value = "下一站，查询需要")
    private String endStation;


}

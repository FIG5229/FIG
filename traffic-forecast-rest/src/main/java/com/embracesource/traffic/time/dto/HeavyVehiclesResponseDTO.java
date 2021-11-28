package com.embracesource.traffic.time.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：wangshimin
 * @date ：Created in 2021/3/23 14:33
 * @description：
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("获取重车数出参实体")
public class HeavyVehiclesResponseDTO {

    @ApiModelProperty("实际数量")
    private Long actualAmount;

    @ApiModelProperty("预测数量")
    private Long expectAmount;
}

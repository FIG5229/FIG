package com.embracesource.traffic.time.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author ：wangshimin
 * @date ：Created in 2021/3/23 14:33
 * @description：
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("获取重车数入参实体")
public class HeavyVehiclesRequestDTO {

    @NotBlank(message = "类型不能为空")
    @ApiModelProperty(value = "1表示卸车站，0表示编组站", required = true)
    private String type;

    @NotBlank(message = "站点不能为空")
    @ApiModelProperty(value = "站点名称", required = true)
    private String station;
}

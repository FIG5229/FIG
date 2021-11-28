package com.embracesource.traffic.time.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ：tom.fu
 * @date ：Created in 2021/5/20
 * @description：模型分析规则修正列表入参dto
 * @version:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "模型分析规则修正分页数据")
public class ModelValidationDTO {

    @ApiModelProperty(value = "总条数")
    private Integer total;

    @ApiModelProperty(value = "状态数据集合")
    private List<ModelValidationResponseDTO> list;

}

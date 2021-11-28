package com.embracesource.traffic.datamanage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ：tom.fu
 * @date ：Created in 2021/5/20
 * @description：状态数据下面列表入参dto
 * @version:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "状态分页数据")
public class StatusDataDTO {

    @ApiModelProperty(value = "总条数")
    private Integer total;

    @ApiModelProperty(value = "状态数据集合")
    private List<StatusDataResponseDTO> list;

}

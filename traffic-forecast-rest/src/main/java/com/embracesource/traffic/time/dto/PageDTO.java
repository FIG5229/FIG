package com.embracesource.traffic.time.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * .
 * User: huqiaomei
 * Date: 2021/3/15 15:34
 * Description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "车辆分页信息")
public class PageDTO {
    @ApiModelProperty(value = "总条数")
    private Integer total;

    @ApiModelProperty(value = "车辆集合")
    private List<CarDTO> list;

}

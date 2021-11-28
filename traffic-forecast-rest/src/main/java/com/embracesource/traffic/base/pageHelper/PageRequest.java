package com.embracesource.traffic.base.pageHelper;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: daniel.liu
 * @Description:
 * @Date: create in 2021/5/21 11:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "分页参数")
public class PageRequest implements Serializable {

    @ApiModelProperty(value = "页数")
    private Integer pageNum;

    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;

    public void defaultParam(){
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
    }
}

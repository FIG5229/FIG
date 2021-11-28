package com.embracesource.traffic.screen.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: daniel.liu
 * @Date: create in 2021/7/21 10:06、
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "请求结果返回实体")
public class ResultInfoDto<T> implements Serializable {



        @ApiModelProperty(value = "标签/类型/x轴/...")
        private String label;

        @ApiModelProperty(value = "数量")
        private T count;


}

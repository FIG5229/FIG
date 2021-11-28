package com.embracesource.traffic.time.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author ：tom.fu
 * @date ：Created in 2021/5/20
 * @description：模型分析规则修正下面列表入参dto
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("模型分析规则修正数据入参实体")
public class ModelValidationRequestDTO {

    @ApiModelProperty(value = "当前站")
    private String currentStand;

     @ApiModelProperty(value = "始发站")
    private String startingStand;

     @ApiModelProperty(value = "终到站")
    private String endStand;

    @NotBlank(message = "每页显示条数不能为空")
    @ApiModelProperty(value = "每页显示条数" , required = true)
    private String pageSize;

    @NotBlank(message = "第几页-不能为空")
    @ApiModelProperty(value = "第几页" , required = true)
    private String page;

    @ApiModelProperty(value = "排序  0 为升序 1 为降序")
    private String sort;







}

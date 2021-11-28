package com.embracesource.traffic.datamanage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author ：tom.fu
 * @date ：Created in 2021/5/20
 * @description：状态数据下面列表入参dto
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("数据处理状态数据入参实体")
public class StatusDataRequestDTO {

    @ApiModelProperty(value = "车号")
    private String wagonNumber;

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





}

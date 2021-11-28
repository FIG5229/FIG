package com.embracesource.traffic.time.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "弹窗展示某站信息")
public class ViewTimeCarInfoDTO {
    @ApiModelProperty(value = "车号")
    private String carNo;

    @ApiModelProperty(value = "起始站")
    private String startStation;

    @ApiModelProperty(value = "终点站")
    private String endStation;

    @ApiModelProperty(value = "当前站")
    private String currentStation;

    @ApiModelProperty(value = "发车时间")
    private String yjCfTime;//预计发车时间

    @ApiModelProperty(value = "到达时间")
    private String yjDdTime;//预计到达时间
}

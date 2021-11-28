package com.embracesource.traffic.time.dto;

import com.embracesource.traffic.base.utils.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
@ApiModel(value = "弹窗某站预测车辆信息")
public class ForecastCarDTO implements Serializable {

    @ApiModelProperty(value = "站点")
    private String currentStation;

    @ApiModelProperty(value = "当前时间")
    private String reportTime;

    @ApiModelProperty(value = "预计出发时间")
    private String yjCfTime;

    @ApiModelProperty(value = "预计到达时间")
    private String yjDdTime;

    @ApiModelProperty(value = "实际到达时间")
    private String sjDdTime;

    @ApiModelProperty(value = "实际出发时间")
    private String sjCfTime;

    @ApiModelProperty(value = "误差值")
    private String errorTime;

    public ForecastCarDTO(String currentStation, String reportTime, String yjCfTime, String yjDdTime) {
        this.currentStation = currentStation;
        this.reportTime = reportTime;
        this.yjCfTime = yjCfTime;
        this.yjDdTime = yjDdTime;
    }

    public ForecastCarDTO(String currentStation, String reportTime, String yjCfTime, String yjDdTime, String sjDdTime, String sjCfTime,String errorTime) {
        this.currentStation = currentStation;
        this.reportTime = reportTime;
        this.yjCfTime = yjCfTime;
        this.yjDdTime = yjDdTime;
        this.sjDdTime = sjDdTime;
        this.sjCfTime = sjCfTime;
        this.errorTime = errorTime;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof ForecastCarDTO){
            ForecastCarDTO t = (ForecastCarDTO)obj;
            return this.currentStation.trim().equals(t.currentStation.trim());
        }else{
            return false;
        }
    }
    @Override
    public int hashCode() {return currentStation.hashCode();}

}

//站点   计划出发时间  里程  计划到达时间  下一站

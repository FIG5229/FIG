package com.embracesource.traffic.freighttrafficcontrast.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("初始加载路径接口返回值")
public class ContrastResponseDTO {

    List<ContrastAllPathResponseDTO> allPath;

    List<ContrastAllPath1ResponseDTO> allPath1;

    String resultSource;

    String car_no;

    StartAndEndStation startAndEndStation;

    int mileage;

    List<ContrastOffline1ResponseDTO> offline1;
}

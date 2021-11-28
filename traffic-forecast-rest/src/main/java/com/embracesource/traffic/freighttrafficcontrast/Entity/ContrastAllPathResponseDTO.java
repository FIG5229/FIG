package com.embracesource.traffic.freighttrafficcontrast.Entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("初始加载路径接口--allpath字段封装对象返回值")
public class ContrastAllPathResponseDTO {

    String zm;

    String dd;

    String cf;

    String chsb;

    String ishit;

    Long time3;

    String lj;

    Double error;

    String tlsj;

    String currentStation;
}

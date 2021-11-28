package com.embracesource.traffic.freighttrafficcontrast.Entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel("初始加载路径接口--offline1字段封装对象返回值")
public class ContrastOffline1ResponseDTO {

   String yjlj;

   String yjcf;

   String lc;

   String yjdd;

   String code;

   String num2;

}

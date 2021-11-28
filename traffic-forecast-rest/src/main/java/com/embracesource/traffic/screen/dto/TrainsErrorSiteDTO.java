package com.embracesource.traffic.screen.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-25 下午 05:28
 * @description：预测错误返回实体
 * @version:
 */
@Data
@ApiModel(value = "错误站点实体")
public class TrainsErrorSiteDTO {

    private String currentStation;

    private Long errorAmount;

}

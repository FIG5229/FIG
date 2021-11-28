package com.embracesource.traffic.forecast.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author wangshimin
 * @Date 2020-11-09 09:10:41
 */

@Data
public class OfflineLzjDO implements Serializable {

    private static final long serialVersionUID = 8108500533656873052L;

    private String currentStation;

    private String nStation;

    private String nodesLz;

}

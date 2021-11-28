package com.embracesource.traffic.forecast.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author wangshimin
 * @Date 2020-11-10 10:34:15
 */

@Data
public class GisGaodeMapPyDO implements Serializable {

    private static final long serialVersionUID = 6289465089861302794L;

    private String name;

    private String py;

}

package com.embracesource.traffic.forecast.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author wangshimin
 * @Date 2020-11-06 10:40:45
 */

@Data
public class GisStationCltsZdDO implements Serializable {

    private static final long serialVersionUID = 1138432278400349407L;

    private String name;

    private String bureau;

    private String longitude;

    private String latitude;

    private String ljdm;

    private String dblm;
}

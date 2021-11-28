package com.embracesource.traffic.forecast.domain;


import lombok.Data;

import java.io.Serializable;

@Data
public class WorkTypeDO implements Serializable {

    private String is_diao;

    private String count;
}

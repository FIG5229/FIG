package com.embracesource.traffic.base.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: daniel.liu
 * @Description:
 * @Date: create in 2021/5/21 9:48
 */
@Getter
@AllArgsConstructor
public enum  WeekEnums {

    Mon(1, "Mon"),
    Tue(2, "Tue"),
    Wed(3, "Wed"),
    Thu(4, "Thu"),
    Fri(5, "Fri"),
    Sat(6, "Sat"),
    Sun(7, "Sun");

    private final Integer day;
    private final String week;
}

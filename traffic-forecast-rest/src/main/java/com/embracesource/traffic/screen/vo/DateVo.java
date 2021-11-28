package com.embracesource.traffic.screen.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-27 下午 02:28
 * @description：
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateVo {
    /**
     * 当前月开始时间
     */
    private Date currentMonthBegin;
    /**
     * 当前月结束时间
     */
    private Date currentMonthEnd;

    /**
     * 当天开始时间
     */
    private Date currentDayBegin;
    /**
     * 当天结束时间
     */
    private Date currentDayEnd;
    /**
     * 当前小时开始时间
     */
    private Date currentHourBegin;
    /**
     * 当前小时结束时间
     */
    private Date currentHourEnd;

}

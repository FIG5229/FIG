package com.embracesource.traffic.time.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * .
 * User: huqiaomei
 * Date: 2021/4/13 16:06
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value ="view_time_page_num" )
public class ViewTimePageNumDo {
    @TableField(value = "day_car_num" )
    private Long dayCarNum;

    @TableField(value = "day_dd_num" )
    private Long dayDdNum;

    @TableField(value = "day_cf_num" )
    private Long dayCfNum;


}

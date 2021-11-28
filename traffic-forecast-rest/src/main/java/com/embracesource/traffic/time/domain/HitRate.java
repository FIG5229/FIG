package com.embracesource.traffic.time.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Date: Created inMon Jan 25 15:21:24 CST 2021
 * @Author: tom.fu
 * @Modified By:
 */
@Data
@TableName(value = "hit_rate")
public class HitRate implements Serializable {

    @TableField(value = "current_station")
    private String currentStation;

    @TableField(value = "hit_rate")
    private Integer hitRate;


}

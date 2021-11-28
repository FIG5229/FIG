package com.embracesource.traffic.screen.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: daniel.liu
 * @Date: create in 2021/6/24 15:25、
 * @Description:
 */
@Data
@TableName("car_error_type_dict")
public class CarErrorTypeDictDo implements Serializable {

    @TableField("error_code")
    private String errorCode;

    @TableField("error_content")
    private String errorContent;
}

package com.embracesource.traffic.admin.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description: 菜单权限表
 * @Date: Created inThu Jan 28 09:58:39 CST 2021
 * @Author: wangshimin
 * @Modified By:
 */
@Data
@TableName(value = "sys_request_menu")
public class SysRequestMenuDO implements Serializable {

    private static final long serialVersionUID = 6339343904728001274L;

    @TableId(value = "id")
    private String id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "url")
    private String url;

    @TableField(value = "description")
    private String description;

}

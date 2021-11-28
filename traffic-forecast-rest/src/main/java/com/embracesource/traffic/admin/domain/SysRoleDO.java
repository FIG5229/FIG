package com.embracesource.traffic.admin.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description: 角色表
 * @Date: Created inThu Jan 28 14:45:58 CST 2021
 * @Author: wangshimin
 * @Modified By:
 */
@Data
@TableName(value = "sys_role")
public class SysRoleDO implements Serializable {

    private static final long serialVersionUID = 9126450177006404866L;

    @TableId(value = "id")
    private String id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "description")
    private String description;

}

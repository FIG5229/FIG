package com.embracesource.traffic.admin.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 用户表
 * @Date: Created inThu Jan 28 09:58:39 CST 2021
 * @Author: wangshimin
 * @Modified By:
 */
@Data
@TableName(value = "sys_user")
public class SysUserDO implements Serializable {

    private static final long serialVersionUID = 1696528309451028203L;

    @TableId(value = "id")
    private String id;

    @TableField(value = "account")
    private String account;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "password")
    private String password;

    @TableField(value = "last_login_time")
    private Date lastLoginTime;

    @TableField(value = "enabled")
    private String enabled;

    @TableField(value = "not_expired")
    private String notExpired;

    @TableField(value = "account_not_locked")
    private String accountNotLocked;

    @TableField(value = "credentials_not_expired")
    private String credentialsNotExpired;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "create_user")
    private Integer createUser;

    @TableField(value = "update_user")
    private Integer updateUser;

}

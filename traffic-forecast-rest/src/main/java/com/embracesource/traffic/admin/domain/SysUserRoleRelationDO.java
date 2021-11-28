package com.embracesource.traffic.admin.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:用户角色表
 * @Date: Created inThu Jan 28 09:58:39 CST 2021
 * @Author: wangshimin
 * @Modified By:
 */
@Data
@TableName(value = "sys_user_role_relation")
public class SysUserRoleRelationDO implements Serializable {

    private static final long serialVersionUID = 7730544413354452475L;

    @TableId(value = "id")
    private String id;

    @TableField(value = "user_id")
    private String userId;

    @TableField(value = "role_id")
    private String roleId;

}

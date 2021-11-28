package com.embracesource.traffic.admin.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description: 角色菜单关系表
 * @Date: Created inThu Jan 28 09:58:39 CST 2021
 * @Author: wangshimin
 * @Modified By:
 */
@Data
@TableName(value ="sys_role_menu_relation" )
public class SysRoleMenuRelationDO implements Serializable {

	private static final long serialVersionUID =  3980825960042956211L;

   	@TableId(value = "id" )
	private String id;

   	@TableField(value = "role_id" )
	private String roleId;

   	@TableField(value = "menu_id" )
	private String menuId;

}

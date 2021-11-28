package com.embracesource.traffic.admin.service;

import com.embracesource.traffic.base.utils.Result;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-28 下午 02:59
 * @description：
 * @version:
 */
public interface RoleService {


    Result createRole(String name, String desc);

    Result modifyRole(String roleId, String name, String desc);

    Result addToRole(String userId, String roleId);

    Result getAllSysRoleList();

    Result getSysRoleList(String userId);

    Result removeUserSysRole(String userId, String roleId);

    Result removeSysRole(String roleId);
}

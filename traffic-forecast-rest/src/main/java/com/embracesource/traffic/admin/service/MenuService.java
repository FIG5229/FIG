package com.embracesource.traffic.admin.service;

import com.embracesource.traffic.base.utils.Result;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-28 下午 02:06
 * @description：
 * @version:
 */
public interface MenuService {

    Result getMenusByUser();

    Result removeRoleSysMenu(String roleId, String menuId);

    Result createSysMenu(String name, String url, String desc);

    Result modifySysMenu(String id, String name, String url, String desc);

    Result addRoleSysMenu(String roleId, String menuId);

    Result removeSysMenu(Long menuId);
}

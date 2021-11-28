package com.embracesource.traffic.admin.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.embracesource.traffic.admin.dao.SysRequestMenuDao;
import com.embracesource.traffic.admin.dao.SysRoleMenuRelationDao;
import com.embracesource.traffic.admin.domain.SysRequestMenuDO;
import com.embracesource.traffic.admin.domain.SysRoleMenuRelationDO;
import com.embracesource.traffic.admin.service.MenuService;
import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.base.utils.SecurityUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-28 下午 02:06
 * @description：
 * @version:
 */
@Service
@DS(value = "pgsql")
public class MenuServiceImpl implements MenuService {
    @Autowired
    SysRequestMenuDao sysRequestMenuDao;
    @Autowired
    SysRoleMenuRelationDao sysRoleMenuRelationDao;

    @Override
    public Result getMenusByUser() {
        String currentUserName = SecurityUserUtil.getCurrentUserName();
        if (StringUtils.isEmpty(currentUserName)) {
            return Result.error("请先登录");
        }
        return Result.ok(sysRequestMenuDao.queryListByUserName(currentUserName));
    }

    @Override
    public Result removeSysMenu(Long menuId) {
        if (sysRequestMenuDao.deleteById(menuId) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @Override
    public Result removeRoleSysMenu(String roleId, String menuId) {
        if (sysRoleMenuRelationDao.deleteByRoleIdAndMenuId(roleId, menuId) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @Override
    public Result createSysMenu(String name, String url, String desc) {
        SysRequestMenuDO sysRequestMenuDO = new SysRequestMenuDO();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        sysRequestMenuDO.setId(uuid);
        sysRequestMenuDO.setName(name);
        sysRequestMenuDO.setUrl(url);
        sysRequestMenuDO.setDescription(desc);
        if (sysRequestMenuDao.insert(sysRequestMenuDO) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @Override
    public Result modifySysMenu(String id, String name, String url, String desc) {
        SysRequestMenuDO sysRequestMenuDO = new SysRequestMenuDO();
        sysRequestMenuDO.setId(id);
        sysRequestMenuDO.setName(name);
        sysRequestMenuDO.setUrl(url);
        sysRequestMenuDO.setDescription(desc);
        if (sysRequestMenuDao.updateById(sysRequestMenuDO) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @Override
    public Result addRoleSysMenu(String roleId, String menuId) {
        SysRoleMenuRelationDO sysRoleMenuRelationDO = new SysRoleMenuRelationDO();
        sysRoleMenuRelationDO.setMenuId(menuId);
        sysRoleMenuRelationDO.setRoleId(roleId);
        sysRoleMenuRelationDO.setId(UUID.randomUUID().toString().replace("-", ""));
        if (sysRoleMenuRelationDao.insert(sysRoleMenuRelationDO) > 0) {
            return Result.ok();
        }
        return Result.error();
    }
}

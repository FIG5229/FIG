package com.embracesource.traffic.admin.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.embracesource.traffic.admin.dao.SysRoleDao;
import com.embracesource.traffic.admin.dao.SysUserRoleRelationDao;
import com.embracesource.traffic.admin.domain.SysRoleDO;
import com.embracesource.traffic.admin.domain.SysUserRoleRelationDO;
import com.embracesource.traffic.admin.service.RoleService;
import com.embracesource.traffic.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-28 下午 02:59
 * @description：
 * @version:
 */
@Service
@DS(value = "pgsql")
public class RoleServiceImpl implements RoleService {

    @Autowired
    SysRoleDao sysRoleDao;

    @Autowired
    SysUserRoleRelationDao sysUserRoleRelationDao;

    @Override
    public Result createRole(String name, String desc) {
        SysRoleDO sysRoleDO = new SysRoleDO();
        sysRoleDO.setName(name);
        sysRoleDO.setDescription(desc);
        sysRoleDO.setId(UUID.randomUUID().toString().replace("-", ""));
        if (sysRoleDao.insert(sysRoleDO) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @Override
    public Result modifyRole(String roleId, String name, String desc) {
        SysRoleDO sysRoleDO = new SysRoleDO();
        sysRoleDO.setName(name);
        sysRoleDO.setDescription(desc);
        sysRoleDO.setId(roleId);
        if (sysRoleDao.updateById(sysRoleDO) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @Override
    public Result addToRole(String userId, String roleId) {
        SysUserRoleRelationDO sysUserRoleRelationDO = new SysUserRoleRelationDO();
        sysUserRoleRelationDO.setRoleId(roleId);
        sysUserRoleRelationDO.setUserId(userId);
        sysUserRoleRelationDO.setId(UUID.randomUUID().toString().replace("-", ""));
        if (sysUserRoleRelationDao.insert(sysUserRoleRelationDO) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @Override
    public Result getAllSysRoleList() {
        List<SysRoleDO> sysRoleDOS = sysRoleDao.selectList(null);
        return Result.ok(sysRoleDOS);
    }

    @Override
    public Result getSysRoleList(String userId) {
        return Result.ok(sysRoleDao.queryListByUserId(userId));
    }

    @Override
    public Result removeUserSysRole(String userId, String roleId) {
        if (sysUserRoleRelationDao.deleteByUserIdAndRoleId(userId, roleId) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @Override
    public Result removeSysRole(String roleId) {
        if (sysRoleDao.deleteById(roleId) > 0) {
            return Result.ok();
        }
        return Result.error();
    }
}

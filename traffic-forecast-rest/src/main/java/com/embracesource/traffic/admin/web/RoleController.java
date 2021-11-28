package com.embracesource.traffic.admin.web;

import com.embracesource.traffic.admin.service.RoleService;
import com.embracesource.traffic.base.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "角色controller", tags = {"角色操作接口"})
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("增加角色")
    @PostMapping("create")
    public Result createRole(String name, String desc) {
        if (StringUtils.isEmpty(name)) {
            return Result.deny("请传入角色名");
        }
        return roleService.createRole(name, desc);
    }

    @ApiOperation("修改角色属性")
    @PostMapping("modify")
    public Result modifyRole(String roleId, String name, String desc) {
        if (null == roleId) {
            return Result.deny("请传入角色id");
        }
        if (StringUtils.isEmpty(name)) {
            return Result.deny("请传入角色名");
        }
        return roleService.modifyRole(roleId, name, desc);
    }

    @ApiOperation("添加用户为xxx角色")
    @PostMapping("add/to")
    public Result addToRole(String userId, String roleId) {
        if (null == roleId) {
            return Result.deny("请传入角色id");
        }
        if (null == userId) {
            return Result.deny("请传入用户id");
        }
        return roleService.addToRole(userId, roleId);
    }

    @ApiOperation("查看所有系统角色（管理员才可以查看）")
    @GetMapping("list/all/get")
    public Result getAllSysRoleList() {
        return roleService.getAllSysRoleList();
    }

    @ApiOperation("查看xx用户的角色列表")
    @GetMapping("list/user/get")
    public Result getSysRoleList(String userId) {
        if (null == userId) {
            return Result.deny("请传入用户id");
        }
        return roleService.getSysRoleList(userId);
    }

    @ApiOperation("移除xx用户的一个角色")
    @PostMapping("/user/remove")
    public Result removeUserSysRole(String userId, String roleId) {
        if (null == userId || null == roleId) {
            return Result.deny("请传入用户id和角色id");
        }
        return roleService.removeUserSysRole(userId, roleId);
    }

    @ApiOperation("移除一个角色")
    @PostMapping("/remove")
    public Result removeSysRole(String roleId) {
        if (null == roleId) {
            return Result.deny("请传入角色id");
        }
        return roleService.removeSysRole(roleId);
    }
}

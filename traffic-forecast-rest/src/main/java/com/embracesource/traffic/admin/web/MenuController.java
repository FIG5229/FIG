package com.embracesource.traffic.admin.web;

import com.embracesource.traffic.admin.service.MenuService;
import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.base.utils.SecurityUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "权限菜单controller", tags = {"权限菜单操作接口"})
@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @ApiOperation("获取当前用户的系统菜单")
    @GetMapping("/currentMenu")
    public Result currentMenu() {
        return menuService.getMenusByUser();
    }

    @ApiOperation("获取当前用户")
    @GetMapping("/currentUser")
    public Result currentUser() {
        /**
         * @Author: Galen
         * @Description: 获取request的其他信息
         **/
        return Result.ok(SecurityUserUtil.getCurrentUser());
    }

    @ApiOperation("增加权限菜单（把资源加入权限管理）")
    @PostMapping("create")
    public Result createSysMenu(String name, String url, String desc) {
        if (StringUtils.isEmpty(name)) {
            return Result.deny("请传入菜单名称");
        }
        if (StringUtils.isEmpty(url)) {
            return Result.deny("请传入菜单地址");
        }
        return menuService.createSysMenu(name, url, desc);
    }

    @ApiOperation("更新权限菜单")
    @PostMapping("modify")
    public Result modifySysMenu(String id, String name, String url, String desc) {
        if (null == id) {
            return Result.deny("请传入权限菜单id");
        }
        return menuService.modifySysMenu(id, name, url, desc);
    }


    @ApiOperation(value = "移除xx角色的一个权限资源", notes = "业务上，权限取消勾选")
    @PostMapping("/role/remove")
    public Result removeRoleSysMenu(String roleId, String menuId) {
        if (null == roleId || null == menuId) {
            return Result.deny("菜单id和角色id不能为空");
        }
        return menuService.removeRoleSysMenu(roleId, menuId);
    }

    @ApiOperation(value = "增加xx角色的一个权限资源", notes = "业务上，权限取消勾选")
    @PostMapping("/role/add")
    public Result AddRoleSysMenu(String roleId, String menuId) {
        if (null == roleId || null == menuId) {
            return Result.deny("菜单id和角色id不能为空");
        }
        return menuService.addRoleSysMenu(roleId, menuId);
    }

    @ApiOperation("移除一个权限资源")
    @PostMapping("/remove")
    public Result removeSysMenu(Long menuId) {
        if (null == menuId) {
            return Result.deny("菜单id不能为空");
        }
        return menuService.removeSysMenu(menuId);
    }


}

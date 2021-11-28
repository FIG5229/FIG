package com.embracesource.traffic.admin.web;

import com.embracesource.traffic.admin.domain.SysUserDO;
import com.embracesource.traffic.admin.service.UserService;
import com.embracesource.traffic.base.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "用户controller", tags = {"用户操作接口"})
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("添加用户")
    @PostMapping("create")
    public Result createUser(SysUserDO sysUserDO) {
        if (sysUserDO == null) {
            Result.deny("请传入需要参数");
        }
        return userService.createUser(sysUserDO);
    }

    @ApiOperation("删除用户")
    @PostMapping("delete")
    public Result deleteUser(String userId) {
        if (userId == null) {
            Result.deny("请传入需要参数");
        }
        return userService.deleteUser(userId);
    }


}

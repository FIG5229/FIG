package com.embracesource.traffic.admin.web;

import com.embracesource.traffic.base.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "登录", tags = "登录，注销等操作")
@RestController
public class LoginController {

    @ApiOperation(value = "swagger端测试登录入口")
    @PostMapping("/login")
    public Result<String> login(String username, String password) {
        System.out.println(username + "------" + password);
        return Result.ok("登录成功!");
    }

    @ApiOperation(value = "swagger端测试注销入口")
    @PostMapping("/logout")
    public Result<String> logout() {
        System.out.println("注销成功!");
        return Result.ok("注销成功!");
    }

    @ApiOperation(value = "提示跳转到登录页面")
    @GetMapping("/login_p")
    public Result<String> loginP() {
        return Result.deny("登录信息失效，请重新登录");
    }


}

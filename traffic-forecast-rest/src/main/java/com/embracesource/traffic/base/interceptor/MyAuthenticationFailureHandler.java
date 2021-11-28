package com.embracesource.traffic.base.interceptor;

import com.embracesource.traffic.base.utils.Result;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-28 上午 09:13
 * @description：
 * @version:
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        Result result;
        if (exception instanceof BadCredentialsException ||
                exception instanceof UsernameNotFoundException) {
            result = Result.error("账户名或者密码输入错误!");
        } else if (exception instanceof LockedException) {
            result = Result.error("账户被锁定，请联系管理员!");
        } else if (exception instanceof CredentialsExpiredException) {
            result = Result.error("密码过期，请联系管理员!");
        } else if (exception instanceof AccountExpiredException) {
            result = Result.error("账户过期，请联系管理员!");
        } else if (exception instanceof DisabledException) {
            result = Result.error("账户被禁用，请联系管理员!");
        } else {
            result = Result.error("登录失败!");
        }
        response.setStatus(201);
        new GalenWebMvcWrite().writeToWeb(response, result);
    }
}

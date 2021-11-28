package com.embracesource.traffic.base.interceptor;

import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.base.utils.SecurityUserUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-28 上午 09:16
 * @description：
 * @version:
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        Result result = Result.ok("登录成功!", SecurityUserUtil.getCurrentUserName());
        new GalenWebMvcWrite().writeToWeb(response, result);
    }
}

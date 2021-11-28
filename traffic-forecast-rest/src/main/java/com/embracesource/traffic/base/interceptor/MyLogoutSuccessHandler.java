package com.embracesource.traffic.base.interceptor;

import com.embracesource.traffic.base.utils.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-28 上午 09:17
 * @description：
 * @version:
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        Result respBean = Result.ok("注销成功!");
        new GalenWebMvcWrite().writeToWeb(response, respBean);
    }
}

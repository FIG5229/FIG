package com.embracesource.traffic.base.interceptor;

import com.embracesource.traffic.base.utils.Result;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-28 上午 10:12
 * @description：会话信息过期策略
 * @version:
 */
@Component
public class CustomizeSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        Result result = Result.error("会话过期");
        HttpServletResponse httpServletResponse = sessionInformationExpiredEvent.getResponse();
        httpServletResponse.setContentType("text/json;charset=utf-8");
        new GalenWebMvcWrite().writeToWeb(httpServletResponse, result);
    }
}

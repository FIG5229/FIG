package com.embracesource.traffic.base.interceptor;

import com.embracesource.traffic.base.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-28 上午 09:20
 * @description：
 * @version:
 */
public class GalenWebMvcWrite {
    public void writeToWeb(HttpServletResponse response, Result respBean) throws IOException {
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.write(om.writeValueAsString(respBean));
        out.flush();
        out.close();
    }
}

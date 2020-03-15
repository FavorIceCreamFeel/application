package com.smxr.application.utils.jwtUtils;

import lombok.extern.java.Log;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author smxr
 * @date 2020/3/11
 * @time 18:41
 * Spring security其他异常处理类,比如请求路径不存在等，
 * 如果不配置此类，则Spring security默认会跳转到登录页面
 */
@Log
@Component
public class MyAuthenticationException implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(200);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = httpServletResponse.getWriter();
        httpServletResponse.getWriter().write("\"result\":\"404\"}");
        printWriter.flush();
        printWriter.close();
    }
}

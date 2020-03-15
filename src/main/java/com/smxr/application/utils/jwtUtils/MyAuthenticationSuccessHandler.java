package com.smxr.application.utils.jwtUtils;

import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * @author smxr
 * @date 2020/3/11
 * @time 18:44
 * security登录成功处理类,返回jwt
 */
@Log
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        //从authentication中获取用户信息
        final User userDetail = (User) authentication.getPrincipal();
        String name = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        log.info(authorities.toString());
        log.info(authentication.getDetails().toString());
        log.info(authentication.getCredentials().toString());
        log.info(authentication.getPrincipal().toString());
        //生成jwt
        String token =  JwtConfigUtils.createJwtToken(userDetail.getUsername());
        httpServletResponse.addHeader("token", "Bearer " + token);
        httpServletResponse.getWriter().write("{\"result\":\"true\"}");
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
    }
}

package com.smxr.application.utils.jwtUtils;

import com.smxr.application.service.UserService;
import com.smxr.application.service.impl.UserServiceImpl;
import io.jsonwebtoken.Claims;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author smxr
 * @date 2020/3/11
 * @time 18:52
 * token 过滤器，在这里解析token，拿到该用户角色，设置到springsecurity的上下文环境中，让springsecurity自动判断权限
 * 所有请求最先进入此过滤器，包括登录接口，而且在springsecurity的密码验证之前执行
 */

@Log
@Component
public class MyJwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("进入token过滤器");
        String authHeader = httpServletRequest.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ") && SecurityContextHolder.getContext().getAuthentication() == null) {
            String authToken = authHeader.substring("Bearer ".length());
            if (authToken.equals(""))
                return;
            Claims claims = null;
            log.info("获得token："+authToken);
            try {
                log.info("测试：开始获得：claims");
                claims = JwtConfigUtils.getClaim(authToken);
                log.info("测试：拿到claims1："+claims);
            } catch (Exception e) {
                logger.info("token：验证失败");
                return;
            }
            log.info("测试：拿到claims2："+claims);
            //验证token,具体怎么验证看需求，可以只验证token不查库，把权限放在jwt中即可
            if(!JwtConfigUtils.isTokenExpired(claims)){//token过期
                logger.info("token过期" + authToken);
                return;
            }else{
                logger.info("token没过期，放行" + authToken);
                //这里只要告诉springsecurity权限即可，账户密码就不用提供验证了，这里我们把UserDetails传给springsecurity，以便以后我们获取当前登录用户
                String string = claims.get("role").toString();
                if (string==null||string.equals("")) {
                    return;
                }
                String substring1 = string.substring(0, string.length() - 2);
                String substring2 = substring1.substring(1);
                String replace = substring2.replace("{authority=", "");
                String replace1 = replace.replace("}", "");
                log.info("从claims中获得权限："+replace1);
                //权限集合
                List<GrantedAuthority> role = AuthorityUtils.commaSeparatedStringToAuthorityList(replace1);
                log.info("封装后的role:"+role);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(new User(claims.getAudience(), "", role), null, role);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

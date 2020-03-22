package com.smxr.application.config;

import com.smxr.application.service.UserService;
import com.smxr.application.utils.jwtUtils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import java.security.SecureRandom;

/**
 * @author smxr
 * @date 2019/11/23
 * @time 13:07
 * SpringSecurity配置文件
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    private MyAuthenticationException myAuthenticationException;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailHandler myAuthenticationFailHandler;
    @Autowired
    private MyJwtTokenFilter myJwtTokenFilter;
    @Autowired
    private SessionRegistry sessionRegistry;
    @Autowired
    private UserService userServer;
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher(){return new HttpSessionEventPublisher();}
    @Bean
    public SessionRegistry sessionRegistry(){return new SessionRegistryImpl(); }
    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2B,15,new SecureRandom());}
    /**
     * 认证权限加密
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.userDetailsService(userServer).passwordEncoder(passwordEncoder());

    }

    /**
     * 制定权限规则
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.authorizeRequests()
                .antMatchers("/favicon.ico","/zero/**","/addOrder/shows","/common/**","/css/**","/images/**","/js/**","/json/**","/jsplug/**","/layui/**","/static/**","/showlmag/**").permitAll()
                .antMatchers("/user/**","/").hasAuthority("SSR")
                .antMatchers("/role/**").hasAuthority("Role")
                .antMatchers("/accueil/**").hasAuthority("Role1")
                .antMatchers("/Goods/**").hasAuthority("Role2")
                .antMatchers("/order/**").hasAuthority("Role3")
                .antMatchers("/fileUpload").hasAuthority("Role4")
                .antMatchers("/shoppingTrolley/**").hasAuthority("Role5")
                .and()
//                .formLogin().loginPage("/zero/login").successForwardUrl("/zero/index").failureForwardUrl("/zero/reLogin")
//                登陆成功和登陆失败接口实现
                .formLogin().loginPage("/zero/login").successHandler(myAuthenticationSuccessHandler).failureHandler(myAuthenticationFailHandler)
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/zero/login").invalidateHttpSession(true).deleteCookies("JESSIONID")
//                .logout().logoutUrl("/logout").logoutSuccessUrl("/zero/login").invalidateHttpSession(true).deleteCookies("JESSIONID")
                .and()
//                记住用户
//                .rememberMe().rememberMeParameter("remember")
//                .and()
//                处理异常和权限不足的接口实现
                .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler).authenticationEntryPoint(myAuthenticationException)
//                .exceptionHandling().accessDeniedPage("/zero/err")
                .and()
//                开启跨域请求
                .csrf().disable().cors();

        http
//                session管理，由于前后端分离，不启用
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                .sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry).expiredUrl("/zero/login")
//                .and()
//                .invalidSessionUrl("/zero/index");//session失效后跳转
//                .maximumSessions(1).maxSessionsPreventsLogin(true);
        http.addFilterBefore(myJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

}

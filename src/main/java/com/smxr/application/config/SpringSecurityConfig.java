package com.smxr.application.config;

import com.smxr.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

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
    private SessionRegistry sessionRegistry;
    @Autowired
    private UserService userServer;
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher(){return new HttpSessionEventPublisher();}
    @Bean
    public SessionRegistry sessionRegistry(){return new SessionRegistryImpl(); }
    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
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
                .formLogin().loginPage("/zero/login").successForwardUrl("/zero/index").failureForwardUrl("/zero/reLogin")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/zero/login").invalidateHttpSession(true).deleteCookies("JESSIONID")
                .and()
                .rememberMe().rememberMeParameter("remember")
                .and()
                .exceptionHandling().accessDeniedPage("/zero/err")
                .and()
                .csrf().disable();

        http
                .sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry).expiredUrl("/zero/login")
                .and()
                .invalidSessionUrl("/zero/index");//session失效后跳转
//                .maximumSessions(1).maxSessionsPreventsLogin(true);
    }

}

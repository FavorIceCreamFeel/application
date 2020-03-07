
package com.smxr.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author smxr
 * @date 2020/1/15
 * @time 11:35
*/

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:E:/upload/");//图片存放路径和映射地址
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");//静态文件存放映射地址
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/resources/");
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/public/");
    }
}


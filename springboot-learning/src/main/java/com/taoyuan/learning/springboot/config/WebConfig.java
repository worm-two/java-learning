package com.taoyuan.learning.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-23 12:51
 * @Description:
 * @Version: 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {

        // 允许跨域的路径
        registry.addMapping("/**")
                // 允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许发送Cookie
                .allowCredentials(true)
                // 允许跨域的请求方法
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 允许的header属性
                .allowedHeaders("*")
                // 跨域实践
                .maxAge(3600);

    }
}

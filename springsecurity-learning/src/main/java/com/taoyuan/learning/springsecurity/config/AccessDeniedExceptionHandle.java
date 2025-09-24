package com.taoyuan.learning.springsecurity.config;

import cn.hutool.json.JSONUtil;
import com.taoyuan.sun.common.global.result.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-23 10:42
 * @Description:
 * @Version: 1.0
 */
@Component
public class AccessDeniedExceptionHandle implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result<Object> fail = Result.fail("权限不足");
        response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONUtil.toJsonStr(fail));
    }



    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        // 设置允许跨域请求的域名
        config.addAllowedOriginPattern("*");
        // 设置允许的请求方式
        config.addAllowedMethod("*");
        // 设置允许的header属性
        config.addAllowedHeader("*");
        // 是否允许cookie
        config.setAllowCredentials(true);
        // 跨域允许时间
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 设置允许跨域的路径
        source.registerCorsConfiguration("/**", config);
        return source;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors((cors) -> cors.configurationSource(corsConfigurationSource()));
        return http.build();

    }
}



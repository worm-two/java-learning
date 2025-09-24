package com.taoyuan.learning.springsecurity.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-19 12:27
 * @Description:
 * @Version: 1.0
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {


    // 验证用户
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        return super.attemptAuthentication(request, response);
    }

    // 验证成功后执行的方法
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }


}






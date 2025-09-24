package com.taoyuan.learning.springsecurity.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-23 13:15
 * @Description:
 * @Version: 1.0
 */
@Component
public class AuthenticationSuccess implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        System.out.println("认证成功");
    }
}

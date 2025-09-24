package com.taoyuan.learning.springsecurity.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-23 13:26
 * @Description:
 * @Version: 1.0
 */
@Component
public class LogoutSuccess implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        System.out.println("注销成功");
    }
}

package com.taoyuan.learning.springsecurity.config;

import cn.hutool.json.JSONUtil;
import com.taoyuan.sun.common.global.result.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-23 10:42
 * @Description:
 * @Version: 1.0
 */
@Component
public class AuthenticationExceptionHandle implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result<Object> fail = Result.fail("登录失败,用户名或密码错误");
        response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONUtil.toJsonStr(fail));
    }
}

package com.taoyuan.learning.springsecurity.config;

import cn.hutool.core.util.StrUtil;
import com.taoyuan.learning.springsecurity.util.JwtUtil;
import com.taoyuan.sun.common.global.exception.BusinessException;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-19 15:46
 * @Description:
 * @Version: 1.0
 */
@Configuration
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 一 获取token(为空表示未第一次发送请求,走登录流程)
        String token = request.getHeader("Authorization");
        if (StrUtil.isBlankIfStr(token)) {
            // 放行
            filterChain.doFilter(request, response);
            return;
        }


        // 二 解析token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            throw new BusinessException("token解析失败");
        }

        // 从redis中获取用户信息
        String redisKey = "login:" + userId;
        SystemUserDetails systemUserDetails = (SystemUserDetails) redisTemplate.opsForValue().get(redisKey);
         if (systemUserDetails == null) {
            throw new BusinessException("用户未登录");
        }

        // 三 存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(systemUserDetails, null, systemUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 四 放行
        filterChain.doFilter(request, response);
    }
}


package com.taoyuan.learning.springsecurity.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-19 12:31
 * @Description:
 * @Version: 1.0
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Resource
    private AuthenticationConfiguration configuration;

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Resource
    private AuthenticationExceptionHandle authenticationExceptionHandle;

    @Resource
    private AccessDeniedExceptionHandle accessDeniedExceptionHandle;


    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 基于token，不需要csrf(CSRF 跨站请求伪造:是一种网络攻击，攻击者通过欺骗已登录用户，诱使他们在不知情的情况下向受信任的网站发送请求。)
        http.csrf(AbstractHttpConfigurer::disable);

        // 基于token，不需要session
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 跨域
        http.cors(withDefaults());

        // 异常处理
        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint(authenticationExceptionHandle)
                .accessDeniedHandler(accessDeniedExceptionHandle));

        // 放行api
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/user/login").permitAll()
                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
        );

        // 启用HTTP Basic认证(可选，常用于 API)
        // http.httpBasic(Customizer.withDefaults());

        // 添加自定义过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}






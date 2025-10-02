package com.taoyuan.learning.springsecurity.config;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-24 14:34
 * @Description:
 * @Version: 1.0
 */
public class CustomerAuthenticationException extends AuthenticationException {

    public CustomerAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CustomerAuthenticationException(String msg) {
        super(msg);
    }
}

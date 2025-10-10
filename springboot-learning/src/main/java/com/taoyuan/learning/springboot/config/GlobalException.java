package com.taoyuan.learning.springboot.config;

import com.taoyuan.sun.common.global.exception.GlobalExceptionHandle;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-29 18:07
 * @Description:
 * @Version: 1.0
 */
@RestControllerAdvice
public class GlobalException extends GlobalExceptionHandle {

    @Override
    public void print(Throwable exception) {
        String message = exception.getMessage();
        System.out.println("message = " + message);
    }
}

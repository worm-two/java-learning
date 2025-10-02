package com.taoyuan.learning.springboot.config;

import com.taoyuan.sun.common.global.exception.GlobalExceptionHandle;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public void print(Exception e, HttpServletRequest request, HttpServletResponse response) {
        super.print(e,request,response);
        String message = e.getMessage();
        System.out.println("message = " + message);
    }
}

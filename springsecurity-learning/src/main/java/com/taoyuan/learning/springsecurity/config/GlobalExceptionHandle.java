package com.taoyuan.learning.springsecurity.config;

import com.taoyuan.sun.common.global.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-22 19:45
 * @Description:
 * @Version: 1.0
 */
// @RestControllerAdvice
public class GlobalExceptionHandle {


    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        return Result.fail();
    }

}

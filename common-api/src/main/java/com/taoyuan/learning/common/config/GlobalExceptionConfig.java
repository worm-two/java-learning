package com.taoyuan.learning.common.config;

import com.taoyuan.sun.common.global.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-29 18:07
 * @Description:
 * @Version: 1.0
 */
// @RestControllerAdvice
public class GlobalExceptionConfig {


    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

}

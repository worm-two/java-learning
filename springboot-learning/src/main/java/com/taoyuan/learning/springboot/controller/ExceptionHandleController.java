package com.taoyuan.learning.springboot.controller;

import com.taoyuan.sun.common.global.result.Result;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-29 17:57
 * @Description:
 * @Version: 1.0
 */
@RestController("exception")
public class ExceptionHandleController {


    @GetMapping("test")
    public Result<String> get() {
        System.out.println(1 / 0);
        return Result.success();
    }


}





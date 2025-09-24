package com.taoyuan.learning.springsecurity.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-19 10:34
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("security")
public class SecurityController {

    @GetMapping("/hello")
    public String sayHello(){
        return "hello security";
    }

}

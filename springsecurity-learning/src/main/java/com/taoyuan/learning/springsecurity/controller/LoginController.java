package com.taoyuan.learning.springsecurity.controller;

import com.taoyuan.learning.entity.mybatis.plus.SystemUser;
import com.taoyuan.learning.springsecurity.service.LoginService;
import com.taoyuan.sun.common.global.result.Result;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-19 15:35
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("user")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("login")
    public Result<SystemUser> find(@RequestBody SystemUser systemUser) {
        return loginService.login(systemUser);
    }

    @GetMapping("query")
    public Result<String> query() {


        // Result.success("毕竟几人真得鹿，不知终日梦为鱼");
        return Result.success("仗剑当空千里去,一更别我二更回");
    }

    @GetMapping("logout")
    public Result<String> logout() {
        return loginService.logout();
    }


    @PreAuthorize("hasAuthority('test123')")
    @GetMapping("test")
    public Result<String> test() {
        return loginService.test();
    }

}

package com.taoyuan.learning.mybatisplus.controller;


import com.taoyuan.learning.mybatisplus.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2025-09-13 16:59:43
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;


}


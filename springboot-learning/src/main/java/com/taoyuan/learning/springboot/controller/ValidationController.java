package com.taoyuan.learning.springboot.controller;

import com.taoyuan.learning.springboot.model.entity.UserInfo;
import com.taoyuan.learning.springboot.service.UserInfoService;
import com.taoyuan.sun.common.global.result.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-05 15:30
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("validate")
@Validated
public class ValidationController {

    @Resource
    private UserInfoService userInfoService;

    @PostMapping("serviceBody")
    public Result<String> serviceBody() {
        userInfoService.insertPerson(new UserInfo());
        return Result.success();
    }

    @PostMapping("servicePath")
    public Result<String> servicePath() {
        userInfoService.getUserInfo(10,"");
        return Result.success();
    }


    @PostMapping("request")
    public Result<String> request(@RequestParam("id") @Max(value = 5, message = "超过 id 的范围了") Integer id) {
        System.out.println("id = " + id);
        return Result.success("success");
    }


   @PostMapping("body")
   public Result<String> post(@RequestBody @Valid UserInfo userInfo) {
       System.out.println("userInfo = " + userInfo);
       return Result.success("success");
   }

    @PostMapping("path/{id}")
    public Result<String> test(@PathVariable("id") @Max(value = 5, message = "超过 id 的范围了") Integer id) {

        return Result.success("success");
    }




}



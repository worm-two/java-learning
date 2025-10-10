package com.taoyuan.learning.springboot.service.impl;

import com.taoyuan.learning.springboot.model.entity.UserInfo;
import com.taoyuan.learning.springboot.service.UserInfoService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-05 18:33
 * @Description:
 * @Version: 1.0
 */
@Service
@Validated
public class UserInfoServiceImpl implements UserInfoService {

    @Override
    public UserInfo getUserInfo(@NotNull @Min(10) Integer id, @NotNull String name) {
        return null;
    }

    @Override
    public void insertPerson(UserInfo userInfo) {

    }
}

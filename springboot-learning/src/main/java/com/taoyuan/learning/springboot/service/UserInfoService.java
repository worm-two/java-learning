package com.taoyuan.learning.springboot.service;

import com.taoyuan.learning.springboot.model.entity.UserInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-05 18:32
 * @Description:
 * @Version: 1.0
 */
public interface UserInfoService {

    UserInfo getUserInfo(@NotNull @Min(10) Integer id, @NotNull String name);

    void insertPerson(@Valid UserInfo userInfo);

}

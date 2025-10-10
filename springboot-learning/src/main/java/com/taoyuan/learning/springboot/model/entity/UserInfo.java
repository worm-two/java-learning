package com.taoyuan.learning.springboot.model.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-05 15:29
 * @Description:
 * @Version: 1.0
 */

@Data
public class UserInfo {

    private Long userId;

    @NotNull(message = "用户名不能为空")
    @Length(min = 2, max = 10)
    private String userName;

    @NotNull(message = "用户账号不能为空")
    @Length(min = 6, max = 20)
    private String account;

    //每个注解对应不同的校验规则，并可制定校验失败后的信息：
    @NotNull(message = "用户密码不能为空")
    @Length(min = 6, max = 20)
    private String password;
}
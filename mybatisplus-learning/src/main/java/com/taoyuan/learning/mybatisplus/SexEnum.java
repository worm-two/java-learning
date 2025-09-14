package com.taoyuan.learning.mybatisplus;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-13 19:33
 * @Description:
 * @Version: 1.0
 */
@AllArgsConstructor
@Getter
public enum SexEnum {

    MALE(1, "男"),
    FEMALE(2, "女");

    @EnumValue
    private final Integer code;

    @JsonValue
    private final String name;


}

package com.taoyuan.learning.mybatisplus;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-13 16:55
 * @Description:
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Integer age;

    private String email;


    @TableField(typeHandler = JacksonTypeHandler.class)
    private SexEnum sex;

    @Version
    private Integer version;

    private int isDeleted;

    // public String getSex() {
    //     return sex.getSexName();
    // }
}

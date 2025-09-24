package com.taoyuan.learning.entity.mybatis.plus;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-22 11:58
 * @Description:
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@TableName("employee")
public class SystemUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 1905122041950251207L;


    private Long id;

    @TableField("name")
    private String username;

    private String password;

    private int age;

}

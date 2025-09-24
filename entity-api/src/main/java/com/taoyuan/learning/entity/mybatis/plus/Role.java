package com.taoyuan.learning.entity.mybatis.plus;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-22 12:00
 * @Description:
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@TableName("role")
public class Role {

    private Long id;

    private String name;

    private String url;

    private String type;

}

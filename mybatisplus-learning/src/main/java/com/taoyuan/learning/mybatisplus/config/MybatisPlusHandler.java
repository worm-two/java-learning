package com.taoyuan.learning.mybatisplus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-13 21:50
 * @Description:
 * @Version: 1.0
 */

@Component
public class MybatisPlusHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        // 获取登录的用户信息
        // User user = ThreadLocalUtils.get();
        // 创建时间
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        // 更新时间
        this.setFieldValByName("ts", LocalDateTime.now(), metaObject);
        // 创建者ID
        // this.setFieldValByName("creatorId", user.getId(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间
        this.setFieldValByName("ts", LocalDateTime.now(), metaObject);
    }

}
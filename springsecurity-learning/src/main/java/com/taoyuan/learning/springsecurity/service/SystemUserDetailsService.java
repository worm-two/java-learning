package com.taoyuan.learning.springsecurity.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.taoyuan.learning.entity.mybatis.plus.SystemUser;
import com.taoyuan.learning.mapper.mybatis.plus.mapper.SystemUserMapper;
import com.taoyuan.learning.springsecurity.config.SystemUserDetails;
import com.taoyuan.sun.common.global.exception.BusinessException;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-19 12:26
 * @Description:
 * @Version: 1.0
 */
@Configuration
public class SystemUserDetailsService implements UserDetailsService {

    @Resource
    private SystemUserMapper systemUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws RuntimeException {
        // 一 查询用户信息
        LambdaQueryWrapper<SystemUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SystemUser::getUsername, username);
        SystemUser systemUser = systemUserMapper.selectOne(queryWrapper);
        if (systemUser == null) {
            throw new BusinessException("用户名或密码错误");
        }

        // 二 查询对应的权限
        List<String> list = new ArrayList<>();
        list.add("test");

        // 三 返回UserDetail对象
        return new SystemUserDetails(systemUser, list);
    }

}

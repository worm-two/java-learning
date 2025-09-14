package com.taoyuan.learning.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.learning.mybatisplus.User;
import com.taoyuan.learning.mybatisplus.dao.UserMapper;
import com.taoyuan.learning.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2025-09-13 16:59:44
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}



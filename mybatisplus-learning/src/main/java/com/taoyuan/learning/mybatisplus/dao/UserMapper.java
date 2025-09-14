package com.taoyuan.learning.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.learning.mybatisplus.User;
import org.apache.ibatis.annotations.Param;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2025-09-13 16:59:43
 */
public interface UserMapper extends BaseMapper<User> {

    Page<User> selectPageVo(@Param("page") Page<User> page, @Param("age")
    Integer age);

}


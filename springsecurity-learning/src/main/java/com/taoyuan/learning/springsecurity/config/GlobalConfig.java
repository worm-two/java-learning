package com.taoyuan.learning.springsecurity.config;

import com.taoyuan.learning.mapper.mybatis.MybatisPlusMapperConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-22 12:28
 * @Description:
 * @Version: 1.0
 */
@Import(value = {MybatisPlusMapperConfig.class})
@Configuration
public class GlobalConfig {


}

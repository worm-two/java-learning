package com.taoyuan.learning.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-10 10:31
 * @Description:
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.taoyuan.learning.mybatis.dao")
public class MybatisApplicationService {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplicationService.class, args);
        System.out.println("Mybatis Application Started!");
    }
}

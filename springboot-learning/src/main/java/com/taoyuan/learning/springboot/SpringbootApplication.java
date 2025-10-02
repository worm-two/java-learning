package com.taoyuan.learning.springboot;

import com.taoyuan.learning.springboot.config.CommonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-23 12:50
 * @Description:
 * @Version: 1.0
 */
@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootApplication.class, args);
        CommonConfig bean = context.getBean(CommonConfig.class);
        System.out.println("bean = " + bean);
    }
}

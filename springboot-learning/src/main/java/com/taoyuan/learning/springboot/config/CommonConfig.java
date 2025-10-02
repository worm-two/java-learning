package com.taoyuan.learning.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-29 17:11
 * @Description:
 * @Version: 1.0
 */
@Component
public class CommonConfig {

    // @Value("${worker.name}")
    private String useranme;

    @Value("${poem}")
    private String poem;
}

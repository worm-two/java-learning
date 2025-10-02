package com.taoyuan.learning.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-29 11:31
 * @Description:
 * @Version: 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "app-settings")
@PropertySource(value = {"classpath:custom-config.yaml"}, encoding = "UTF-8",factory = YamlMapSourceFactory.class)
// @PropertySource(value = {"classpath:custom-config.properties"}, encoding = "UTF-8")
public class CustomYamlConfig {

    private String apiKey;
    private Database database;

    @Data
    public static class Database {
        private String url;
        private String username;
    }
}

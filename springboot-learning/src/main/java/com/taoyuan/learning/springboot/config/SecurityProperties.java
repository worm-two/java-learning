package com.taoyuan.learning.springboot.config;

import lombok.Data;

import java.util.List;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-26 15:49
 * @Description:
 * @Version: 1.0
 */
@Data
// @ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    /**
     * 白名单 URL 集合
     */
    private List<String> ignoreUrls;

    /**
     * JWT 配置
     */
    private JwtProperty jwt;


    /**
     * JWT 配置
     */
    @Data
    public static class JwtProperty {

        /**
         * JWT 密钥
         */
        private String key;

        /**
         * JWT 过期时间
         */
        private Long ttl;

    }
}

package com.taoyuan.learning;

/**
 * @Author: yuming
 * @CreateTime: 2025-08-16 17:34
 * @Description: ${description}
 * @Version: 1.0
 */
public class Main {
    public static void main(String[] args) {
        LoggerService loggerService = LoggerService.getService();
        loggerService.info("你好");
        loggerService.debug("测试Java SPI 机制");
    }
}
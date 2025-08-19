package com.taoyuan.learning;

/**
 * @Author: yuming
 * @CreateTime: 2025-08-16 17:13
 * @Description:
 * @Version: 1.0
 */
public class Main {
    public static void main(String[] args) {
        LoggerService service = LoggerService.getService();

        service.info("Hello SPI");
        service.debug("Hello SPI");
    }
}
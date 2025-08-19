package com.taoyuan.learning;

/**
 * @Author: yuming
 * @CreateTime: 2025-08-16 17:15
 * @Description:
 * @Version: 1.0
 */
public class Logback implements Logger {
    @Override
    public void info(String s) {
        System.out.println("Logback info 打印日志：" + s);
    }

    @Override
    public void debug(String s) {
        System.out.println("Logback debug 打印日志：" + s);
    }
}
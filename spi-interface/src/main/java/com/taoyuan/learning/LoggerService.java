package com.taoyuan.learning;

import java.util.Optional;
import java.util.ServiceLoader;

/**
 * @Author: yuming
 * @CreateTime: 2025-08-16 17:12
 * @Description:
 * @Version: 1.0
 */
public class LoggerService {
    private static final LoggerService SERVICE = new LoggerService();

    private final Logger logger;

    private LoggerService() {
        ServiceLoader<Logger> loader = ServiceLoader.load(Logger.class);
        Optional<Logger> first = loader.findFirst();
        logger = first.orElse(null);
    }

    public static LoggerService getService() {
        return SERVICE;
    }

    public void info(String msg) {
        if (logger == null) {
            System.out.println("info 中没有发现 Logger 服务提供者");
        } else {
            logger.info(msg);
        }
    }

    public void debug(String msg) {
        if (logger == null) {
            System.out.println("debug 中没有发现 Logger 服务提供者");
        } else {
            logger.info(msg);
        }
    }
}
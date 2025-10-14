package learning.taoyuan.com.dubbo.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-13 17:50
 * @Description:
 * @Version: 1.0
 */
@EnableDubbo
@SpringBootApplication
public class DubboProviderApplicationService {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplicationService.class, args);
    }
}

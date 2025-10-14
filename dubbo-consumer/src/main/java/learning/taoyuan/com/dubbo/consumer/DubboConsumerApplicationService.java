package learning.taoyuan.com.dubbo.consumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-13 18:04
 * @Description:
 * @Version: 1.0
 */

@EnableDubbo
@SpringBootApplication
public class DubboConsumerApplicationService {

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplicationService.class, args);
    }
}

package learning.taoyuan.com.nacos.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-10 18:22
 * @Description:
 * @Version: 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosRegisterApplicationService {

    public static void main(String[] args) {
        SpringApplication.run(NacosRegisterApplicationService.class, args);
    }
}

package learning.taoyuan.com.nacos.register.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-12 22:23
 * @Description:
 * @Version: 1.0
 */
@RefreshScope
@RestController
public class DemoController {


    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}

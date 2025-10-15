package learning.taoyuan.com.zookeeper.util;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-14 17:19
 * @Description:
 * @Version: 1.0
 */
@Configuration
public class ServiceRegistry {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private int port;

    @Resource
    private ZookeeperUtil zookeeperUtil;

    /**
     * 应用启动后注册服务到 ZooKeeper
     */
    // @Bean
    public ApplicationRunner registerService() {
        return args -> {
            // 服务注册路径：/services/{appName}/{ip:port}
            String servicePath = "/services/" + appName + "/" + "127.0.0.1:" + port;
            // 存储服务元数据（如权重、版本等）
            String serviceData = "{\"weight\":1,\"version\":\"1.0.0\"}";
            // 创建临时节点（服务下线后自动删除）
            zookeeperUtil.createEphemeralNode(servicePath, serviceData);
            System.out.println("服务注册成功：" + servicePath);
        };
    }
}
package learning.taoyuan.com.zookeeper.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-14 17:16
 * @Description:
 * @Version: 1.0
 */
@Configuration
public class ZookeeperConfig {

    @Value("${zookeeper.server}")
    private String zkServer;

    @Value("${zookeeper.session-timeout}")
    private int sessionTimeout;

    @Value("${zookeeper.connection-timeout}")
    private int connectionTimeout;

    @Value("${zookeeper.retry.base-sleep-time}")
    private int baseSleepTime;

    @Value("${zookeeper.retry.max-retries}")
    private int maxRetries;

    /**
     * 初始化 Curator 客户端
     */
    @Bean(initMethod = "start", destroyMethod = "close")
    public CuratorFramework curatorFramework() {
        // 重试策略：指数退避重试
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTime, maxRetries);

        // 构建客户端
        return CuratorFrameworkFactory.builder()
                .connectString(zkServer)
                .sessionTimeoutMs(sessionTimeout)
                .connectionTimeoutMs(connectionTimeout)
                .retryPolicy(retryPolicy)
                //.namespace("zookeeper-demo") // 命名空间（可选，用于隔离不同应用）
                .build();
    }
}

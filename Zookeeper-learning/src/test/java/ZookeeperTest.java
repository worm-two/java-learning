import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-14 18:05
 * @Description:
 * @Version: 1.0
 */
public class ZookeeperTest {

    CuratorFramework curatorFramework;

    @BeforeEach
    public void beforeAll() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("192.168.2.60:2181")
                .sessionTimeoutMs(60000)
                .connectionTimeoutMs(15000)
                .retryPolicy(retryPolicy).build();
    }


    @Test
    public void test1() throws Exception {
        curatorFramework.start();

        curatorFramework.create()
                // 自动创建父节点
                .creatingParentsIfNeeded()
                // 持久化节点（服务下线后不删除）
                .withMode(CreateMode.PERSISTENT)
                .forPath("/role", "剑子仙迹".getBytes(StandardCharsets.UTF_8));
    }

}

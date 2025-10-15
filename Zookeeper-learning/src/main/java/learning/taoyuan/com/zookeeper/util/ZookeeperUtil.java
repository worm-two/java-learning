package learning.taoyuan.com.zookeeper.util;

import jakarta.annotation.Resource;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.zookeeper.CreateMode;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-14 17:17
 * @Description:
 * @Version: 1.0
 */
@Component
public class ZookeeperUtil {

    @Resource
    private CuratorFramework curatorFramework;

    /**
     * 创建节点（持久化节点）
     */
    public void createPersistentNode(String path, String data) throws Exception {
        curatorFramework.create()
                // 自动创建父节点
                .creatingParentsIfNeeded()
                // 持久化节点（服务下线后不删除）
                .withMode(CreateMode.PERSISTENT)
                .forPath(path, data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 创建临时节点（服务下线后自动删除，适合服务注册）
     */
    public void createEphemeralNode(String path, String data) throws Exception {
        curatorFramework.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL) // 临时节点
                .forPath(path, data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 获取节点数据
     */
    public String getNodeData(String path) throws Exception {
        byte[] data = curatorFramework.getData().forPath(path);
        return data != null ? new String(data, StandardCharsets.UTF_8) : null;
    }

    /**
     * 更新节点数据
     */
    public void updateNodeData(String path, String data) throws Exception {
        curatorFramework.setData().forPath(path, data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 删除节点
     */
    public void deleteNode(String path) throws Exception {
        curatorFramework.delete()
                .deletingChildrenIfNeeded() // 递归删除子节点
                .forPath(path);
    }

    /**
     * 列出子节点
     */
    public List<String> listChildren(String path) throws Exception {
        return curatorFramework.getChildren().forPath(path);
    }

    /**
     * 分布式锁（获取锁）
     */
    public boolean acquireLock(String lockPath, long timeout, TimeUnit unit) throws Exception {
        InterProcessMutex lock = new InterProcessMutex(curatorFramework, lockPath);
        return lock.acquire(timeout, unit);
    }

    /**
     * 释放分布式锁
     */
    public void releaseLock(InterProcessMutex lock) throws Exception {
        if (lock != null && lock.isAcquiredInThisProcess()) {
            lock.release();
        }
    }
}
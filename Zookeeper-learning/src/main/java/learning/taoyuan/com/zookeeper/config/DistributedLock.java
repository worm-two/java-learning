package learning.taoyuan.com.zookeeper.config;

import jakarta.annotation.Resource;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-14 18:22
 * @Description:
 * @Version: 1.0
 */
@Component
public class DistributedLock {

    @Resource
    private CuratorFramework client;

    @Value("${zookeeper.base-lock-path:/distributed-locks}")
    private String baseLockPath;

    // 缓存锁对象，避免重复创建
    private final Map<String, InterProcessMutex> lockCache = new ConcurrentHashMap<>();

    /**
     * 获取锁对象（若不存在则创建）
     */
    private InterProcessMutex getLock(String name) {
        return lockCache.computeIfAbsent(name,
                k -> new InterProcessMutex(client, baseLockPath + "/" + k));
    }

    /**
     * 阻塞式获取锁（直到获得或线程被中断）
     */
    public void acquire(String name) throws Exception {
        InterProcessMutex lock = getLock(name);
        lock.acquire(); // 阻塞等待直到成功或中断
    }

    /**
     * 尝试获取锁，带超时时间
     *
     * @param name 锁名
     * @param time 等待时间
     * @param unit 时间单位
     * @return true: 成功获取锁；false: 超时未获取
     */
    public boolean tryAcquire(String name, long time, TimeUnit unit) throws Exception {
        InterProcessMutex lock = getLock(name);
        return lock.acquire(time, unit);
    }

    /**
     * 释放锁（仅当当前线程持有时才释放）
     */
    public void release(String name) throws Exception {
        InterProcessMutex lock = getLock(name);
        if (lock.isAcquiredInThisProcess()) {
            lock.release();
        }
    }

    /**
     * 判断当前线程是否持有锁
     */
    public boolean isLocked(String name) {
        InterProcessMutex lock = lockCache.get(name);
        return lock != null && lock.isAcquiredInThisProcess();
    }

    /**
     * 强制删除锁节点（仅在异常恢复或测试时使用）
     * 警告：此操作会破坏锁一致性，请谨慎！
     */
    public void forceRelease(String name) throws Exception {
        String lockPath = baseLockPath + "/" + name;
        if (client.checkExists().forPath(lockPath) != null) {
            client.delete().guaranteed().deletingChildrenIfNeeded().forPath(lockPath);
        }
        lockCache.remove(name);
    }

    /**
     * 清空缓存（通常在服务关闭或重启时）
     */
    public void clearAll() {
        lockCache.clear();
    }
}
package learning.taoyuan.com.zookeeper.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * /**
 * 分布式锁服务 (增强版)
 * ✅ 支持重试加锁
 * ✅ 支持断线重连后自动恢复
 * ✅ 安全释放与强制清理机制
 *
 * @Author: yuming
 * @CreateTime: 2025-10-14 18:28
 * @Description:
 * @Version: 1.0
 */
@Slf4j
@Component
public class DistributedLockService {


    @Resource
    private CuratorFramework client;

    @Value("${zookeeper.base-lock-path:/distributed-locks}")
    private String baseLockPath;

    // 缓存锁对象
    private final Map<String, InterProcessMutex> lockCache = new ConcurrentHashMap<>();

    // 记录当前线程持有的锁（用于断线重连恢复）
    private final Map<String, Boolean> acquiredLocks = new ConcurrentHashMap<>();

    /**
     * 初始化时添加 ZooKeeper 连接状态监听器
     */
    @PostConstruct
    public void init() {
        client.getConnectionStateListenable().addListener(connectionListener);
        log.info("✅ DistributedLockService initialized, monitoring ZooKeeper connection state");
    }

    @PreDestroy
    public void shutdown() {
        lockCache.clear();
        acquiredLocks.clear();
    }

    /**
     * 获取锁对象
     */
    private InterProcessMutex getLock(String name) {
        return lockCache.computeIfAbsent(name,
                k -> new InterProcessMutex(client, baseLockPath + "/" + k));
    }

    /**
     * 阻塞式获取锁（带重试）
     */
    public void acquire(String name) throws Exception {
        acquireWithRetry(name, 3, 2000);
    }

    /**
     * 尝试获取锁，带超时
     */
    public boolean tryAcquire(String name, long time, TimeUnit unit) throws Exception {
        InterProcessMutex lock = getLock(name);
        boolean success = lock.acquire(time, unit);
        if (success) {
            acquiredLocks.put(name, true);
            log.info("✅ tryAcquire success for lock: {}", name);
        }
        return success;
    }

    /**
     * 带重试的阻塞加锁逻辑
     */
    private void acquireWithRetry(String name, int maxRetries, long retryIntervalMs) throws Exception {
        InterProcessMutex lock = getLock(name);
        int attempt = 0;
        while (true) {
            try {
                lock.acquire();
                acquiredLocks.put(name, true);
                log.info("✅ Lock acquired: {}", name);
                return;
            } catch (Exception e) {
                attempt++;
                if (attempt >= maxRetries) {
                    log.error("❌ Failed to acquire lock [{}] after {} retries", name, maxRetries, e);
                    throw e;
                }
                log.warn("⚠️ Acquire lock [{}] failed (attempt {}/{}), retrying in {}ms",
                        name, attempt, maxRetries, retryIntervalMs);
                Thread.sleep(retryIntervalMs);
            }
        }
    }

    /**
     * 释放锁
     */
    public void release(String name) throws Exception {
        InterProcessMutex lock = getLock(name);
        if (lock.isAcquiredInThisProcess()) {
            lock.release();
            acquiredLocks.remove(name);
            log.info("🔓 Lock released: {}", name);
        }
    }

    /**
     * 判断当前线程是否持有锁
     */
    public boolean isLocked(String name) {
        return acquiredLocks.getOrDefault(name, false);
    }

    /**
     * 强制释放（仅用于测试或应急恢复）
     */
    public void forceRelease(String name) throws Exception {
        String lockPath = baseLockPath + "/" + name;
        if (client.checkExists().forPath(lockPath) != null) {
            client.delete().guaranteed().deletingChildrenIfNeeded().forPath(lockPath);
            log.warn("⚠️ Force released ZooKeeper lock node: {}", lockPath);
        }
        acquiredLocks.remove(name);
        lockCache.remove(name);
    }

    /**
     * ZooKeeper连接状态监听器
     * 当连接断开/重连时进行处理
     */
    private final ConnectionStateListener connectionListener = (client, newState) -> {
        switch (newState) {
            case LOST:
                log.error("❌ ZooKeeper session lost! All locks are released automatically.");
                acquiredLocks.clear();
                break;
            case RECONNECTED:
                log.info("🔁 ZooKeeper reconnected. Trying to re-acquire previous locks...");
                for (String name : acquiredLocks.keySet()) {
                    try {
                        acquireWithRetry(name, 3, 2000);
                        log.info("✅ Re-acquired lock after reconnect: {}", name);
                    } catch (Exception e) {
                        log.error("❌ Failed to re-acquire lock after reconnect: {}", name, e);
                    }
                }
                break;
            case SUSPENDED:
                log.warn("⚠️ ZooKeeper connection suspended (network issue)");
                break;
            default:
                log.debug("ZooKeeper state changed: {}", newState);
        }
    };
}

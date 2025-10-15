package learning.taoyuan.com.zookeeper.service;

import jakarta.annotation.Resource;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.springframework.stereotype.Service;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-14 17:21
 * @Description:
 * @Version: 1.0
 */
@Service
public class ZookeeperService {

    @Resource
    private CuratorFramework curatorFramework;





    public void watchServices(String path) throws Exception {
        CuratorCache cache = CuratorCache.build(curatorFramework, path);
        cache.start();
        cache.listenable().addListener((type, oldData, data) -> {
            System.out.println("节点变化：" + type + "，路径：" + (data != null ? data.getPath() : null));
        });
    }

}

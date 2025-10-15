package learning.taoyuan.com.zookeeper.controller;

import jakarta.annotation.Resource;
import learning.taoyuan.com.zookeeper.util.ZookeeperUtil;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-14 17:20
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/zk")
public class ZookeeperController {

    @Resource
    private ZookeeperUtil zookeeperUtil;

    // 创建节点
    @PostMapping("/create")
    public String createNode(@RequestParam("path") String path, @RequestParam("data") String data) throws Exception {
        zookeeperUtil.createPersistentNode(path, data);
        return "节点创建成功：" + path;
    }

    // 获取节点数据
    @GetMapping("/get")
    public String getNodeData(@RequestParam("path") String path) throws Exception {
        return zookeeperUtil.getNodeData(path);
    }

    // 列出子节点（如查询服务列表）
    @GetMapping("/list")
    public List<String> listChildren(@RequestParam("path") String path) throws Exception {

        ZooKeeper zk = null;


        return zookeeperUtil.listChildren(path);
    }


}

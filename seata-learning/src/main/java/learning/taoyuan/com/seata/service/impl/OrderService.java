package learning.taoyuan.com.seata.service.impl;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.spring.annotation.GlobalTransactional;
import learning.taoyuan.com.seata.service.TccActionOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-20 14:17
 * @Description:
 * @Version: 1.0
 */
@Service
public class OrderService {

    @Autowired
    private TccActionOne tccActionOne;

    @GlobalTransactional
    public void createOrder(String orderId, String commodityCode, int orderCount) {
        // Try 阶段
        boolean result = tccActionOne.prepareCreateOrder(new BusinessActionContext(), orderId, commodityCode, orderCount);
        if (!result) {
            throw new RuntimeException("预留资源失败");
        }
        // 其他业务逻辑...
    }
}

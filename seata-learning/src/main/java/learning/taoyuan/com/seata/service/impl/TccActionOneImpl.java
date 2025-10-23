package learning.taoyuan.com.seata.service.impl;

import io.seata.rm.tcc.api.BusinessActionContext;
import learning.taoyuan.com.seata.service.TccActionOne;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-20 14:15
 * @Description:
 * @Version: 1.0
 */
@Slf4j
@Component
public class TccActionOneImpl implements TccActionOne {

    @Override
    public boolean prepareCreateOrder(BusinessActionContext context, String orderId, String commodityCode, int orderCount) {
        log.info("prepareCreateOrder");
        // 预留资源逻辑
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext context) {
        log.info("commit");
        // 确认执行逻辑
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext context) {
        log.info("rollback");
        // 取消执行逻辑
        return true;
    }

}

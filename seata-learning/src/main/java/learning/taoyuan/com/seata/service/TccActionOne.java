package learning.taoyuan.com.seata.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-20 14:15
 * @Description:
 * @Version: 1.0
 */
public interface TccActionOne {

    // Try 阶段方法
    @TwoPhaseBusinessAction(name = "prepareCreateOrder", commitMethod = "commit", rollbackMethod = "rollback")
    boolean prepareCreateOrder(BusinessActionContext context, String orderId, String commodityCode, int orderCount);

    // Confirm 阶段方法
    boolean commit(BusinessActionContext context);

    // Cancel 阶段方法
    boolean rollback(BusinessActionContext context);

}

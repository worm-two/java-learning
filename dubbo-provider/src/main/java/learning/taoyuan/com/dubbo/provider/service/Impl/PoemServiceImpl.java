package learning.taoyuan.com.dubbo.provider.service.Impl;

import com.taoyuan.learning.dubbo.api.PoemService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-13 17:16
 * @Description:
 * @Version: 1.0
 */
@DubboService
public class PoemServiceImpl implements PoemService {

    @Override
    public String getPoem() {
        return "千古地仙随风逝，昔曰三王归青冢。阳莽憾陨谁无败？卷土重来再称王。天河一挂淘龙鱼，逆天独行顾八荒。今曰暂且展翼去，明朝登仙笞凤凰 。";
    }
}

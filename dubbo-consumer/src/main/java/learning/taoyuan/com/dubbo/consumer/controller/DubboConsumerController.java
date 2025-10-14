package learning.taoyuan.com.dubbo.consumer.controller;

import com.taoyuan.learning.dubbo.api.PoemService;
import com.taoyuan.sun.common.global.result.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-13 17:46
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("dubbo/consumer")
public class DubboConsumerController {

    @DubboReference
    private PoemService poemService;


    @GetMapping("poem")
    public Result<String> poem() {
        String poem = poemService.getPoem();
        return Result.success(poem);
    }

}

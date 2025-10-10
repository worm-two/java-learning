package com.taoyuan.learning.springboot.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-05 21:32
 * @Description: 监听Job执行情况，实现JobExecutorListener，且在batch配置类里，Job的Bean上绑定该监听器
 * @Version: 1.0
 */
@Slf4j
public class MyJobListener implements JobExecutionListener {


    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("job 开始, id={}",jobExecution.getJobId());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("job 结束, id={}",jobExecution.getJobId());
    }
}
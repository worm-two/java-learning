package com.taoyuan.learning.springboot.batch;

import com.taoyuan.learning.springboot.model.entity.BlogInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;

import static java.lang.String.format;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-05 21:34
 * @Description:
 * @Version: 1.0
 */
@Slf4j
public class MyReadListener implements ItemReadListener<BlogInfo> {


    @Override
    public void beforeRead() {
    }

    @Override
    public void afterRead(BlogInfo item) {
    }

    @Override
    public void onReadError(Exception ex) {
        try {
            log.info(format("%s%n", ex.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

package com.taoyuan.learning;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: yuming
 * @CreateTime: 2025-07-05 15:22
 * @Description:
 * @Version: 1.0
 */
@AllArgsConstructor
@Getter
public enum Season implements Info {

    SPRING("春天", "春暖花开"),
    SUMMER("夏天", "夏日炎炎"),
    AUTUMN("秋天", "秋高气爽"),
    WINTER("冬天", "白雪皑皑");

    private final String SEASON_NAME;
    private final String SEASON_DESC;


    @Override
    public void show() {
        System.out.println("show" );
    }

    public String getSeasonName() {
        return SEASON_NAME;
    }
}

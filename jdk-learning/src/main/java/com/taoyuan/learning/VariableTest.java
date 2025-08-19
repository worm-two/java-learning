package com.taoyuan.learning;

import cn.hutool.core.util.StrUtil;

/**
 * @Author: yuming
 * @CreateTime: 2025-06-21 12:09
 * @Description: 变量测试用例
 * @Version: 1.0
 */
public class VariableTest {

    public static void main(String[] args) {

        String seasonName = Season.SPRING.getSeasonName();
        System.out.println("seasonName = " + seasonName);

        String reverse = StrUtil.reverse(seasonName);
        System.out.println("reverse = " + reverse);


    }

}


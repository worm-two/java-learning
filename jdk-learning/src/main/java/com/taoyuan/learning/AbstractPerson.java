package com.taoyuan.learning;

/**
 * @Author: yuming
 * @CreateTime: 2025-07-05 12:17
 * @Description: 抽象类学习
 * @Version: 1.0
 */
public abstract class AbstractPerson {


    protected abstract void born();

    protected abstract void growth();


    protected abstract void death();


    public void life() {
        born();
        growth();
        death();
        System.out.println("人的一生");
    }

}

package com.taoyuan.learning;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: yuming
 * @CreateTime: 2025-07-05 12:54
 * @Description: 学生
 * @Version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends AbstractPerson {

    @Override
    protected void born() {
        System.out.println("出生");
    }

    @Override
    protected void growth() {
        System.out.println("成长");
    }

    @Override
    protected void death() {
        System.out.println("死亡");
    }
}





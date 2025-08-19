package com.taoyuan.learning;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @Author: yuming
 * @CreateTime: 2025-06-21 12:06
 * @Description: 剑宗
 * @Version: 1.0
 */
// @Getter
public class Son extends Parent {

    private String poem = "孤舟蓑笠翁,独钓寒江雪";


    public void print() {
        System.out.println(this.getPoem());
        System.out.println(super.getPoem());

        Date date = new Date();

        LocalDate localDate = LocalDate.now();

    }

    public void showPoem() {
        System.out.println(this.poem);
    }

    public static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}



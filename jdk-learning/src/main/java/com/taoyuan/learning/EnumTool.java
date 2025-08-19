package com.taoyuan.learning;

import java.util.Random;

/**
 * @Author: yuming
 * @CreateTime: 2025-07-05 15:40
 * @Description:
 * @Version: 1.0
 */
public class EnumTool {

    private static Random rand = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
}

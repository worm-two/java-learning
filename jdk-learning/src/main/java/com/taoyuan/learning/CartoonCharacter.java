package com.taoyuan.learning;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @Author: yuming
 * @CreateTime: 2025-07-05 15:35
 * @Description:
 * @Version: 1.0
 */
enum CartoonCharacter implements Supplier<CartoonCharacter> {

    SLAPPY, SPANKY, PUNCHY,
    SILLY, BOUNCY, NUTTY, BOB;

    private Random rand = new Random(47);

    @Override
    public CartoonCharacter get() {
        CartoonCharacter[] values = values();

        return values()[rand.nextInt(values().length)];
    }
}

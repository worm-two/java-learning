package com.taoyuan;

import com.taoyuan.learning.Logger;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @Author: yuming
 * @CreateTime: 2025-08-17 13:40
 * @Description:
 * @Version: 1.0
 */
public class AppTest {

    public static void main(String[] args) {

        MiniServiceLoader<Logger> loader = MiniServiceLoader.load(Logger.class);


        String encoded = URLEncoder.encode("中文!", StandardCharsets.UTF_8);
        System.out.println(encoded);

    }
}



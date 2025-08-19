package com.taoyuan.learning.elasticsearch.boot.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yuming
 * @CreateTime: 2025-08-16 14:23
 * @Description: elasticsearch接口
 * @Version: 1.0
 */
@RestController
@RequestMapping("elasticsearch")
public class ElasticSearchController {

    @GetMapping("test")
    public String test(HttpServletRequest request) {


        ServletContext servletContext = request.getServletContext();



        return "test";
    }
}

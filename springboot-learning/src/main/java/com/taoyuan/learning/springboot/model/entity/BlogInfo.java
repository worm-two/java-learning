package com.taoyuan.learning.springboot.model.entity;

import lombok.Data;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-05 19:33
 * @Description:
 * @Version: 1.0
 */
@Data
public class BlogInfo {

    private Integer id;

    private String blogAuthor;

    private String blogUrl;

    private String blogTitle;

    private String blogItem;
}

package com.taoyuan.learning.springboot.model.entity;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-07 18:05
 * @Description:
 * @Version: 1.0
 */
@Data
public class ResponseBean {

    private String code;

    private String msg;



}


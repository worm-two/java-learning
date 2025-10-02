package com.taoyuan.learning.springboot.config;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-29 12:12
 * @Description:
 * @Version: 1.0
 */
@Slf4j
public class YamlMapSourceFactory implements PropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name, @NotNull EncodedResource resource) throws IOException {
        // 将yml配置转换为Properties
        Properties propertiesFromYaml = loadYamlIntoProperties(resource);
        // 文件未找到使用默认的配置读取
        if (propertiesFromYaml == null) {
            return (name != null ? new ResourcePropertySource(name, resource) : new ResourcePropertySource(resource));
        }
        String sourceName = name != null ? name : resource.getResource().getFilename();
        if(sourceName == null){
            log.error("获取资源失败");
            throw new RuntimeException("加载资源失败"+resource);
        }
        return new PropertiesPropertySource(sourceName, propertiesFromYaml);
    }

    private Properties loadYamlIntoProperties(EncodedResource resource) {
        try {
            YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
            factory.setResources(resource.getResource());
            factory.afterPropertiesSet();
            return factory.getObject();
        } catch (IllegalStateException e) {
            log.error("加载{}文件失败，请检查是否有该文件", resource.getResource().getFilename());
            return null;
        }
    }
}
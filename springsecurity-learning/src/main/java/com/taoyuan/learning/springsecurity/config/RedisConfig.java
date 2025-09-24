package com.taoyuan.learning.springsecurity.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-22 20:23
 * @Description:
 * @Version: 1.0
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        ObjectMapper objectMapper = createObjectMapper();

        // String的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // value序列化方式采用jackson
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(objectMapper,Object.class);
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // Hash的 key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // Hash的 value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new SimpleModule().addSerializer(Long.class, ToStringSerializer.instance));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

}

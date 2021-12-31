package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @ClassName RedisConfigura
 * @Description redisTemple配置类
 * @Author wangpeng
 * @Date 2021/12/31 11:45
 */
@Configuration
public class RedisConfigura {
    @Autowired
    public RedisTemplate<String,Object> redisTemplate;
}

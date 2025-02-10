package com.lyc.lease.common.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
/**
 * @ClassName RedisConfiguration
 * @Description TODO Redis配置类
 * @Author LuKey_C
 * @Date 2025/2/10 22:06
 * @Version 1.0
 */
@Configuration
public class RedisConfiguration {

    /**
     * 创建RedisTemplate对象，用于操作Redis数据库
     *
     * @param redisConnectionFactory Redis连接工厂
     * @return RedisTemplate对象
     */
    @Bean
    public RedisTemplate<String, Object> stringObjectRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(RedisSerializer.java());
        return template;
    }
}
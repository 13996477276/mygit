package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/**
 * @author wenguanghua
 * @since 2021-05-24 13:43
 */
@Configuration
public class RedisConfig  {
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException{
        RedisTemplate<String,Object> template = new RedisTemplate<String, Object>();
        Jackson2JsonRedisSerializer<Object> redisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        template.setKeySerializer(redisSerializer);
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}

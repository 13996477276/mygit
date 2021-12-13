package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenguanghua
 * @since 2021-05-24 13:22
 */
@RestController
public class TestController {
    @Autowired
    RedisTemplate redisTemplate;
    @GetMapping(value = "/redis")
    public String getRedisData(){
        redisTemplate.opsForValue().set("test","helloRedis");
        String result = (String)redisTemplate.opsForValue().get("test");
        return result;
    };
}

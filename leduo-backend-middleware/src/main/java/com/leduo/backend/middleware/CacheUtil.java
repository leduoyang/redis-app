package com.leduo.backend.middleware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CacheUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final long DEFAULT_TTL = 600L;

    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;
    public static final String NULL_PLACEHOLDER = "NULL";

    public void setValue(String key, Object value, long expirationTime) {
        redisTemplate.opsForValue().set(key, value, expirationTime, DEFAULT_TIME_UNIT);
    }

    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value, DEFAULT_TTL, DEFAULT_TIME_UNIT);
    }

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteCache(String key) {
        redisTemplate.delete(key);
    }
}

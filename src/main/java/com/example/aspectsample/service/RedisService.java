package com.example.aspectsample.service;

import com.example.aspectsample.common.UuidGenerator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Getter
    public enum ResultCode {

        STATUS_SUCCESS("OK"),
        STATUS_FAILURE("NG");

        private final String resultCode;

        ResultCode(String resultCode) {
            this.resultCode = resultCode;
        }
    }

    private final RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void defaultSet() {
        String uuid = UuidGenerator.generateUuid();
        String greeting = "default";
        long ttl = 300;
        redisTemplate.opsForHash().put(uuid, greeting, ResultCode.STATUS_SUCCESS.getResultCode());

        redisTemplate.expire(uuid, ttl, TimeUnit.SECONDS);
    }

    public void set(Object key, Object hashKey, Object hashValue) {
        long ttl = 300;
        redisTemplate.opsForHash().put(key, hashKey, hashValue);

        redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
    }

}

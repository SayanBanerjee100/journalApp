package com.xyz.JournalApp1.journal.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@Service
public class RedisTokenBlacklistService {

    private final StringRedisTemplate redisTemplate;

    public RedisTokenBlacklistService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private String blacklistKey(String token) {
        return "blacklist:token:" + token;
    }

    public void blacklistToken(String token, Date expiry) {

        long ttlMillis = expiry.getTime() - System.currentTimeMillis();

        if (ttlMillis <= 0) {
            return;
        }

        redisTemplate.opsForValue().set(
                blacklistKey(token),
                "true",
                Duration.ofMillis(ttlMillis)
        );
    }

    public boolean isBlacklisted(String token) {
        String value = redisTemplate.opsForValue().get(blacklistKey(token));
        return value != null;
    }
}

package com.xyz.JournalApp1.journal.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisOtpService {

    private final StringRedisTemplate redisTemplate;

    public RedisOtpService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private String otpKey(String emailId) {
        return "otp:reset:" + emailId;
    }

    public void saveOtp(String emailId, String otp) {
        redisTemplate.opsForValue().set(
                otpKey(emailId),
                otp,
                Duration.ofMinutes(10)
        );
    }

    public String getOtp(String emailId) {
        return redisTemplate.opsForValue().get(otpKey(emailId));
    }

    public void deleteOtp(String emailId) {
        redisTemplate.delete(otpKey(emailId));
    }
}

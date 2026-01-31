

package com.xyz.JournalApp1.journal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(
        basePackages = "com.xyz.JournalApp1.journal.repository.redis"
)
public class RedisConfig {
}

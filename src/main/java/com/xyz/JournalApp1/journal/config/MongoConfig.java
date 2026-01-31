package com.xyz.JournalApp1.journal.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.xyz.JournalApp1.journal.repository.mongo"
)
public class MongoConfig {
}
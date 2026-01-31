package com.xyz.JournalApp1.journal.repository.mongo;

import com.xyz.JournalApp1.journal.model.mongo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmailId(String emailId);
}

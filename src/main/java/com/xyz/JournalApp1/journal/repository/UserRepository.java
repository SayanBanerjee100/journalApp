package com.xyz.JournalApp1.journal.repository;

import com.xyz.JournalApp1.journal.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmailId(String emailId);
}

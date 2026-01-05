package com.xyz.JournalApp1.journal.repository;

import com.xyz.JournalApp1.journal.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}

package com.xyz.JournalApp1.journal.repository.mongo;

import com.xyz.JournalApp1.journal.model.mongo.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByUserIdOrderByCreatedAtDesc(String userId);

    Optional<Task> findByIdAndUserId(String id, String userId);
}

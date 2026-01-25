package com.xyz.JournalApp1.journal.repository;

import com.xyz.JournalApp1.journal.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, String> {

    List<Task> findByUserIdOrderByCreatedAtDesc(String userId);

    Optional<Task> findByIdAndUserId(String id, String userId);
}

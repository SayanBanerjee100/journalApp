package com.xyz.JournalApp1.journal.repository.jpa;

import com.xyz.JournalApp1.journal.model.jpa.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserIdOrderByCreatedAtDesc(String userId);

    Optional<Task> findByIdAndUserId(String id, String userId);
}

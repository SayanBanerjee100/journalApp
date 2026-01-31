package com.xyz.JournalApp1.journal.service;

import com.xyz.JournalApp1.journal.dto.task.TaskCreateRequest;
import com.xyz.JournalApp1.journal.dto.task.TaskResponseDTO;
import com.xyz.JournalApp1.journal.dto.task.TaskUpdateRequest;
import com.xyz.JournalApp1.journal.model.jpa.Task;
import com.xyz.JournalApp1.journal.model.mongo.User;
import com.xyz.JournalApp1.journal.repository.jpa.TaskRepository;
import com.xyz.JournalApp1.journal.repository.mongo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        // Works because JWT sets authentication username as email
        String email = org.springframework.security.core.context.SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmailId(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));
    }

    public TaskResponseDTO createTask(TaskCreateRequest request) {
        User user = getCurrentUser();

        Task task = Task.builder()
                .userId(user.getId())
                .title(request.title())
                .description(request.description())
                .build();

        Task saved = taskRepository.save(task);
        return map(saved);
    }

    public List<TaskResponseDTO> getMyTasks() {
        User user = getCurrentUser();

        return taskRepository.findByUserIdOrderByCreatedAtDesc(user.getId())
                .stream()
                .map(this::map)
                .toList();
    }

    public TaskResponseDTO updateTask(String taskId, TaskUpdateRequest request) {
        User user = getCurrentUser();

        Task task = taskRepository.findByIdAndUserId(taskId, user.getId())
                .orElseThrow(() -> new RuntimeException("Task not found or not owned by user"));

        if (request.title() != null) task.setTitle(request.title());
        if (request.description() != null) task.setDescription(request.description());
        if (request.status() != null) task.setStatus(request.status());

        Task updated = taskRepository.save(task);
        return map(updated);
    }

    public void deleteTask(String taskId) {
        User user = getCurrentUser();

        Task task = taskRepository.findByIdAndUserId(taskId, user.getId())
                .orElseThrow(() -> new RuntimeException("Task not found or not owned by user"));

        taskRepository.delete(task);
    }

    private TaskResponseDTO map(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }
}

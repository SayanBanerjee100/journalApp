package com.xyz.JournalApp1.journal.controller;

import com.xyz.JournalApp1.journal.dto.task.TaskCreateRequest;
import com.xyz.JournalApp1.journal.dto.task.TaskResponseDTO;
import com.xyz.JournalApp1.journal.dto.task.TaskUpdateRequest;
import com.xyz.JournalApp1.journal.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskCreateRequest request) {
        return ResponseEntity.ok(taskService.createTask(request));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getMyTasks() {
        return ResponseEntity.ok(taskService.getMyTasks());
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTask(
            @PathVariable String taskId,
            @Valid @RequestBody TaskUpdateRequest request
    ) {
        return ResponseEntity.ok(taskService.updateTask(taskId, request));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable String taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}

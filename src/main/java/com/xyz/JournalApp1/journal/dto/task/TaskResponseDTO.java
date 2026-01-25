package com.xyz.JournalApp1.journal.dto.task;

import com.xyz.JournalApp1.journal.enums.TaskStatus;

import java.time.Instant;

public record TaskResponseDTO(
        String id,
        String title,
        String description,
        TaskStatus status,
        Instant createdAt,
        Instant updatedAt
) {}

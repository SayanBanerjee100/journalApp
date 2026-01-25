package com.xyz.JournalApp1.journal.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskCreateRequest(

        @NotBlank(message = "title is required")
        @Size(max = 200, message = "title must be <= 200 characters")
        String title,

        @Size(max = 2000, message = "description must be <= 2000 characters")
        String description

) {}

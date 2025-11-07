package com.task_flow.dto;

import com.task_flow.model.Priority;
import com.task_flow.model.Status;

import java.time.LocalDate;

public record TaskResponseDTO(
    Long id,
    String title,
    String description,
    Status status,
    Priority priority,
    LocalDate dueDate,
    Long assigneeId,
    String assigneeUsername,
    Long projectId,
    String projectName
) {}
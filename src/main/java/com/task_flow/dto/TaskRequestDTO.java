package com.task_flow.dto;

import com.task_flow.model.Priority;
import com.task_flow.model.Status;

import java.time.LocalDate;

public record TaskRequestDTO(
    String title,
    String description,
    Status status,
    Priority priority,
    LocalDate dueDate,
    Long assigneeId, // ID of the assigned user
    Long projectId  // ID of the associated project
) {}
package com.task_flow.dto;

import java.time.LocalDateTime;

public record NotificationResponseDTO(
    Long id,
    String message,
    boolean read,
    LocalDateTime createdAt,
    Long userId,
    String username
) {}

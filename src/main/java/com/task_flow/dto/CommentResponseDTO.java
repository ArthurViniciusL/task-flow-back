package com.task_flow.dto;

import java.time.LocalDateTime;

public record CommentResponseDTO(
    Long id,
    String content,
    Long taskId,
    Long userId,
    String username, // Username of the commenter
    LocalDateTime createdAt
) {}

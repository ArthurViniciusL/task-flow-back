package com.task_flow.dto;

public record CommentRequestDTO(
    String content,
    Long taskId,
    Long userId // The ID of the user making the comment
) {}

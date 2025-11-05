package com.task_flow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {
    private Long id;
    private String content;
    private Long taskId;
    private Long userId;
    private String username; // Username of the commenter
    private LocalDateTime createdAt;
}

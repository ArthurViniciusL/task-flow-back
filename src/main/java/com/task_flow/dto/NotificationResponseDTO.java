package com.task_flow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponseDTO {
    private Long id;
    private String message;
    private boolean read;
    private LocalDateTime createdAt;
    private Long userId;
    private String username;
}

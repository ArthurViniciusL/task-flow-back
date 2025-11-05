package com.task_flow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDTO {
    private String content;
    private Long taskId;
    private Long userId; // The ID of the user making the comment
}

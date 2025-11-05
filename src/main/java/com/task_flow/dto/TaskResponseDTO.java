package com.task_flow.dto;

import com.task_flow.model.Priority;
import com.task_flow.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDate dueDate;
    private Long assigneeId;
    private String assigneeUsername;
    private Long projectId;
    private String projectName;
}
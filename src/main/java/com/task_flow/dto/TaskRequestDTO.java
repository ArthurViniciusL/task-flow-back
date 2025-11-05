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
public class TaskRequestDTO {
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDate dueDate;
    private Long assigneeId; // ID of the assigned user
    private Long projectId;  // ID of the associated project
}
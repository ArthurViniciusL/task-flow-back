package com.task_flow.dto;

import com.task_flow.model.Status;

import java.util.Map;

public record DashboardResponseDTO(
    Map<Status, Long> taskCountsByStatus
) {}

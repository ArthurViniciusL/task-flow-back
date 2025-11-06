package com.task_flow.dto;

import java.util.Set;

public record ProjectRequestDTO(
        String name,
        String description,
        Set<Long> teamMemberIds // IDs of users involved in the project
) {}
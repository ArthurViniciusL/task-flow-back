package com.task_flow.dto;

import java.util.Set;

public record ProjectResponseDTO(
        Long id,
        String name,
        String description,
        Set<UserResponseDTO> team, // Assuming a UserResponseDTO exists or will be created
        double progressPercentage // For progress summary panel
) {}
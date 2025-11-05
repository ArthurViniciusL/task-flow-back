package com.task_flow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Set<UserResponseDTO> team; // Assuming a UserResponseDTO exists or will be created
    private double progressPercentage; // For progress summary panel
}
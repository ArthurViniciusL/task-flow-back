package com.task_flow.dto;

import com.task_flow.model.Role;

import java.util.Set;

public record UserResponseDTO(
    Long id,
    String username, // Email
    Set<Role> roles
) {}

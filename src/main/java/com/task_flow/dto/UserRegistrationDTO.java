package com.task_flow.dto;

import com.task_flow.model.Role;

import java.util.Set;

public record UserRegistrationDTO(
    String username, // Email
    String password,
    Set<Role> roles
) {}
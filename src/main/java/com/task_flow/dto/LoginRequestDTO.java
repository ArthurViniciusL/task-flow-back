package com.task_flow.dto;

public record LoginRequestDTO(
    String username, // Email
    String password
) {}
package com.task_flow.dto;

public record LoginResponseDTO(
    String token,
    String username
) {}
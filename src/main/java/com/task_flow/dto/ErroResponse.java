package com.task_flow.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ErroResponse(
    @Schema(description = "Timestamp of the error", example = "2025-11-06T10:30:00.000+00:00")
    String timestamp,

    @Schema(description = "HTTP status code", example = "500")
    int status,

    @Schema(description = "Error message", example = "Internal Server Error")
    String error,

    @Schema(description = "Path of the request", example = "/api/tasks")
    String path
) {}

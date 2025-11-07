package com.task_flow.controller;

import com.task_flow.dto.DashboardResponseDTO;
import com.task_flow.service.TaskService;
import com.task_flow.annotation.ApiResponseDocumentation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final TaskService taskService;

    @ApiResponseGet
    @GetMapping
    public ResponseEntity<DashboardResponseDTO> getDashboardData() {
        DashboardResponseDTO dashboardData = new DashboardResponseDTO(taskService.getTaskCountByStatus());
        return ResponseEntity.ok(dashboardData);
    }
}

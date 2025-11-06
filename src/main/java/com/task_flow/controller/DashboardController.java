package com.task_flow.controller;

import com.task_flow.dto.DashboardResponseDTO;
import com.task_flow.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task_flow.model.Status;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<DashboardResponseDTO> getDashboardData() {
        DashboardResponseDTO dashboardData = new DashboardResponseDTO(taskService.getTaskCountByStatus());
        return ResponseEntity.ok(dashboardData);
    }
}

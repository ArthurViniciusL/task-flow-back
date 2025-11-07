package com.task_flow.controller;

import com.task_flow.dto.DashboardResponseDTO;
import com.task_flow.service.TaskService;
import com.task_flow.annotation.ApiResponseDocumentation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "API para visualização de dados agregados do sistema")
public class DashboardController {

    private final TaskService taskService;

    @Operation(summary = "Obter dados do dashboard", description = "Retorna estatísticas e métricas gerais do sistema, incluindo contagem de tarefas por status")
    @ApiResponseGet
    @GetMapping
    public ResponseEntity<DashboardResponseDTO> getDashboardData() {
        DashboardResponseDTO dashboardData = new DashboardResponseDTO(taskService.getTaskCountByStatus());
        return ResponseEntity.ok(dashboardData);
    }
}
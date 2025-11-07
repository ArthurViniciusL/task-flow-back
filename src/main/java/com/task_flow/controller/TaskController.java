package com.task_flow.controller;

import com.task_flow.dto.TaskRequestDTO;
import com.task_flow.dto.TaskResponseDTO;
import com.task_flow.service.TaskService;
import com.task_flow.annotation.ApiResponseDocumentation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "API de gerenciamento de tarefas")
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "Criar nova tarefa", description = "Cria uma nova tarefa no sistema")
    @ApiResponsePost
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO createdTask = taskService.createTask(taskRequestDTO);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todas as tarefas", description = "Retorna a lista completa de tarefas cadastradas no sistema")
    @ApiResponseGetList
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        List<TaskResponseDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @Operation(summary = "Buscar tarefa por ID", description = "Retorna uma tarefa específica pelo seu identificador")
    @ApiResponseGetById
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        TaskResponseDTO task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @Operation(summary = "Listar tarefas por responsável", description = "Retorna todas as tarefas atribuídas a um usuário específico")
    @ApiResponseGetList
    @GetMapping("/assignee/{assigneeId}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByAssignee(@PathVariable Long assigneeId) {
        List<TaskResponseDTO> tasks = taskService.getTasksByAssignee(assigneeId);
        return ResponseEntity.ok(tasks);
    }

    @Operation(summary = "Listar tarefas por projeto", description = "Retorna todas as tarefas associadas a um projeto específico")
    @ApiResponseGetList
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByProjectId(@PathVariable Long projectId) {
        List<TaskResponseDTO> tasks = taskService.getTasksByProjectId(projectId);
        return ResponseEntity.ok(tasks);
    }

    @Operation(summary = "Atualizar tarefa", description = "Atualiza os dados de uma tarefa existente")
    @ApiResponsePut
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO updatedTask = taskService.updateTask(id, taskRequestDTO);
        return ResponseEntity.ok(updatedTask);
    }

    @Operation(summary = "Excluir tarefa", description = "Remove uma tarefa do sistema")
    @ApiResponseDelete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
package com.task_flow.controller;

import com.task_flow.dto.TaskRequestDTO;
import com.task_flow.dto.TaskResponseDTO;
import com.task_flow.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO createdTask = taskService.createTask(taskRequestDTO);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        List<TaskResponseDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        TaskResponseDTO task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/assignee/{assigneeId}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByAssignee(@PathVariable Long assigneeId) {
        List<TaskResponseDTO> tasks = taskService.getTasksByAssignee(assigneeId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByProjectId(@PathVariable Long projectId) {
        List<TaskResponseDTO> tasks = taskService.getTasksByProjectId(projectId);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO updatedTask = taskService.updateTask(id, taskRequestDTO);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
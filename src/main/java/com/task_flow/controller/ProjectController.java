package com.task_flow.controller;

import com.task_flow.dto.ProjectRequestDTO;
import com.task_flow.dto.ProjectResponseDTO;
import com.task_flow.service.ProjectService;
import com.task_flow.annotation.ApiResponseDocumentation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @ApiResponsePost
    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody ProjectRequestDTO projectRequestDTO) {
        ProjectResponseDTO createdProject = projectService.createProject(projectRequestDTO);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @ApiResponseGetList
    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjects() {
        List<ProjectResponseDTO> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @ApiResponseGetById
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> getProjectById(@PathVariable Long id) {
        ProjectResponseDTO project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    @ApiResponsePut
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> updateProject(@PathVariable Long id, @RequestBody ProjectRequestDTO projectRequestDTO) {
        ProjectResponseDTO updatedProject = projectService.updateProject(id, projectRequestDTO);
        return ResponseEntity.ok(updatedProject);
    }

    @ApiResponseDelete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
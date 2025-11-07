package com.task_flow.controller;

import com.task_flow.dto.ProjectRequestDTO;
import com.task_flow.dto.ProjectResponseDTO;
import com.task_flow.service.ProjectService;
import com.task_flow.annotation.ApiResponseDocumentation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@Tag(name = "Projetos", description = "API de gerenciamento de projetos")
public class ProjectController {

    private final ProjectService projectService;

    @Operation(summary = "Criar novo projeto", description = "Cria um novo projeto no sistema")
    @ApiResponsePost
    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody ProjectRequestDTO projectRequestDTO) {
        ProjectResponseDTO createdProject = projectService.createProject(projectRequestDTO);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos os projetos", description = "Retorna a lista completa de projetos cadastrados")
    @ApiResponseGetList
    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjects() {
        List<ProjectResponseDTO> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @Operation(summary = "Buscar projeto por ID", description = "Retorna um projeto específico pelo seu identificador")
    @ApiResponseGetById
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> getProjectById(@PathVariable Long id) {
        ProjectResponseDTO project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    @Operation(summary = "Atualizar projeto", description = "Atualiza os dados de um projeto existente")
    @ApiResponsePut
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> updateProject(@PathVariable Long id, @RequestBody ProjectRequestDTO projectRequestDTO) {
        ProjectResponseDTO updatedProject = projectService.updateProject(id, projectRequestDTO);
        return ResponseEntity.ok(updatedProject);
    }

    @Operation(summary = "Excluir projeto", description = "Remove um projeto do sistema")
    @ApiResponseDelete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
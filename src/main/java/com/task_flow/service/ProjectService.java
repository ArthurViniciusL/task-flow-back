package com.task_flow.service;

import com.task_flow.dto.ProjectRequestDTO;
import com.task_flow.dto.ProjectResponseDTO;
import com.task_flow.dto.UserResponseDTO;
import com.task_flow.exception.ProjectNotFoundException;
import com.task_flow.exception.UserNotFoundException;
import com.task_flow.model.Project;
import com.task_flow.model.User;
import com.task_flow.repository.ProjectRepository;
import com.task_flow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final UserService userService; // To convert User to UserResponseDTO

    //Metodo que cria um projeto
    public ProjectResponseDTO createProject(ProjectRequestDTO requestDTO) {
        Project project = new Project();
        project.setName(requestDTO.name());
        project.setDescription(requestDTO.description());

        if (requestDTO.teamMemberIds() != null && !requestDTO.teamMemberIds().isEmpty()) {
            Set<User> teamMembers = requestDTO.teamMemberIds().stream()
                    .map(this::findUserById)
                    .collect(Collectors.toSet());
            project.setTeam(teamMembers);
        }

        Project savedProject = projectRepository.save(project);
        return convertToProjectResponseDTO(savedProject);
    }

    //Metodo que lista todos os projetos
    public List<ProjectResponseDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::convertToProjectResponseDTO)
                .collect(Collectors.toList());
    }

    //Metodo que lista um projeto pelo ID
    public ProjectResponseDTO getProjectById(Long id) {
        Project project = findProjectById(id);
        return convertToProjectResponseDTO(project);
    }

    //Metodo que atualiza um projeto
    public ProjectResponseDTO updateProject(Long id, ProjectRequestDTO requestDTO) {
        Project existingProject = findProjectById(id);

        existingProject.setName(requestDTO.name());
        existingProject.setDescription(requestDTO.description());

        if (requestDTO.teamMemberIds() != null) {
            Set<User> teamMembers = requestDTO.teamMemberIds().stream()
                    .map(this::findUserById)
                    .collect(Collectors.toSet());
            existingProject.setTeam(teamMembers);
        } else {
            existingProject.setTeam(new HashSet<>()); // Clear team if no IDs provided
        }

        Project updatedProject = projectRepository.save(existingProject);
        return convertToProjectResponseDTO(updatedProject);
    }

    //Metodo que deleta um projeto
    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException("Project not found with ID: " + id);
        }
        projectRepository.deleteById(id);
    }

    //Metodo que lista um projeto pelo ID
    private Project findProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with ID: " + projectId));
    }

    //Metodo que lista um usuario pelo ID
    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    //Metodo que converte um projeto em DTO
    private ProjectResponseDTO convertToProjectResponseDTO(Project project) {
        Set<UserResponseDTO> teamResponseDTOs = project.getTeam().stream()
                .map(userService::convertToUserResponseDTO)
                .collect(Collectors.toSet());


        double progressPercentage = 0.0;

        return new ProjectResponseDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                teamResponseDTOs,
                progressPercentage
        );
    }}
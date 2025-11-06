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

    public List<ProjectResponseDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::convertToProjectResponseDTO)
                .collect(Collectors.toList());
    }

    public ProjectResponseDTO getProjectById(Long id) {
        Project project = findProjectById(id);
        return convertToProjectResponseDTO(project);
    }

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

    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException("Project not found with ID: " + id);
        }
        projectRepository.deleteById(id);
    }

    private Project findProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with ID: " + projectId));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    private ProjectResponseDTO convertToProjectResponseDTO(Project project) {
        Set<UserResponseDTO> teamResponseDTOs = project.getTeam().stream()
                .map(userService::convertToUserResponseDTO)
                .collect(Collectors.toSet());

        // Placeholder for progress calculation, as it's not defined in the current context
        double progressPercentage = 0.0; // You might implement actual progress calculation here

        return new ProjectResponseDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                teamResponseDTOs,
                progressPercentage
        );
    }}
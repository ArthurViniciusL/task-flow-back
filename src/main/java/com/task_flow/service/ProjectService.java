package com.task_flow.service;

import com.task_flow.dto.ProjectRequestDTO;
import com.task_flow.dto.ProjectResponseDTO;
import com.task_flow.dto.UserResponseDTO;
import com.task_flow.model.Project;
import com.task_flow.model.User;
import com.task_flow.repository.ProjectRepository;
import com.task_flow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO) {
        Project project = new Project();
        project.setName(projectRequestDTO.getName());
        project.setDescription(projectRequestDTO.getDescription());

        if (projectRequestDTO.getTeamMemberIds() != null && !projectRequestDTO.getTeamMemberIds().isEmpty()) {
            Set<User> teamMembers = projectRequestDTO.getTeamMemberIds().stream()
                    .map(id -> userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID: " + id)))
                    .collect(Collectors.toSet());
            project.setTeam(teamMembers);
        }

        Project savedProject = projectRepository.save(project);
        return convertToDto(savedProject);
    }

    public List<ProjectResponseDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ProjectResponseDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return convertToDto(project);
    }

    public ProjectResponseDTO updateProject(Long id, ProjectRequestDTO projectRequestDTO) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        existingProject.setName(projectRequestDTO.getName());
        existingProject.setDescription(projectRequestDTO.getDescription());

        if (projectRequestDTO.getTeamMemberIds() != null && !projectRequestDTO.getTeamMemberIds().isEmpty()) {
            Set<User> teamMembers = projectRequestDTO.getTeamMemberIds().stream()
                    .map(userId -> userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId)))
                    .collect(Collectors.toSet());
            existingProject.setTeam(teamMembers);
        } else {
            existingProject.setTeam(new HashSet<>()); // Clear team if no IDs provided
        }

        Project updatedProject = projectRepository.save(existingProject);
        return convertToDto(updatedProject);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    private ProjectResponseDTO convertToDto(Project project) {
        ProjectResponseDTO dto = new ProjectResponseDTO();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());

        if (project.getTeam() != null) {
            Set<UserResponseDTO> teamDtos = project.getTeam().stream()
                    .map(user -> new UserResponseDTO(user.getId(), user.getUsername(), user.getRoles()))
                    .collect(Collectors.toSet());
            dto.setTeam(teamDtos);
        }

        // Calculate progress percentage (example: based on completed tasks)
        long totalTasks = project.getTasks().size();
        long completedTasks = project.getTasks().stream()
                .filter(task -> task.getStatus() == com.task_flow.model.Status.DONE)
                .count();
        if (totalTasks > 0) {
            dto.setProgressPercentage((double) completedTasks / totalTasks * 100);
        }
        return dto;
    }
}
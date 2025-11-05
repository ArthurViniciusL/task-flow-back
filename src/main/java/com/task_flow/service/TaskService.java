package com.task_flow.service;

import com.task_flow.dto.TaskRequestDTO;
import com.task_flow.dto.TaskResponseDTO;
import com.task_flow.model.Task;
import com.task_flow.model.User;
import com.task_flow.model.Project;
import com.task_flow.repository.TaskRepository;
import com.task_flow.repository.UserRepository;
import com.task_flow.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        Task task = new Task();
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setStatus(taskRequestDTO.getStatus());
        task.setPriority(taskRequestDTO.getPriority());
        task.setDueDate(taskRequestDTO.getDueDate());

        if (taskRequestDTO.getAssigneeId() != null) {
            User assignee = userRepository.findById(taskRequestDTO.getAssigneeId())
                    .orElseThrow(() -> new RuntimeException("Assignee not found"));
            task.setAssignee(assignee);
        }

        if (taskRequestDTO.getProjectId() != null) {
            Project project = projectRepository.findById(taskRequestDTO.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Project not found"));
            task.setProject(project);
        }

        Task savedTask = taskRepository.save(task);
        return convertToDto(savedTask);
    }

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TaskResponseDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return convertToDto(task);
    }

    public TaskResponseDTO updateTask(Long id, TaskRequestDTO taskRequestDTO) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        existingTask.setTitle(taskRequestDTO.getTitle());
        existingTask.setDescription(taskRequestDTO.getDescription());
        existingTask.setStatus(taskRequestDTO.getStatus());
        existingTask.setPriority(taskRequestDTO.getPriority());
        existingTask.setDueDate(taskRequestDTO.getDueDate());

        if (taskRequestDTO.getAssigneeId() != null) {
            User assignee = userRepository.findById(taskRequestDTO.getAssigneeId())
                    .orElseThrow(() -> new RuntimeException("Assignee not found"));
            existingTask.setAssignee(assignee);
        } else {
            existingTask.setAssignee(null); // Allow unassigning
        }

        if (taskRequestDTO.getProjectId() != null) {
            Project project = projectRepository.findById(taskRequestDTO.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Project not found"));
            existingTask.setProject(project);
        } else {
            existingTask.setProject(null); // Allow disassociating from project
        }

        // Set lastModifiedBy to the currently authenticated user
        // This assumes the user is authenticated and their username is available in the SecurityContext
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));
        existingTask.setLastModifiedBy(currentUser);

        Task updatedTask = taskRepository.save(existingTask);
        return convertToDto(updatedTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Map<Status, Long> getTaskCountByStatus() {
        Map<Status, Long> taskCounts = new HashMap<>();
        for (Status status : Status.values()) {
            taskCounts.put(status, taskRepository.countByStatus(status));
        }
        return taskCounts;
    }

    public List<TaskResponseDTO> getTasksByAssignee(Long assigneeId) {
        User assignee = userRepository.findById(assigneeId)
                .orElseThrow(() -> new RuntimeException("Assignee not found with ID: " + assigneeId));
        return taskRepository.findByAssignee(assignee).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<TaskResponseDTO> getTasksByProjectId(Long projectId) {
        // No need to check for project existence here, as the repository method handles it
        return taskRepository.findByProjectId(projectId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private TaskResponseDTO convertToDto(Task task) {
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setDueDate(task.getDueDate());

        if (task.getAssignee() != null) {
            dto.setAssigneeId(task.getAssignee().getId());
            dto.setAssigneeUsername(task.getAssignee().getUsername());
        }
        if (task.getProject() != null) {
            dto.setProjectId(task.getProject().getId());
            dto.setProjectName(task.getProject().getName()); // Assuming Project has a 'name' field
        }
        return dto;
    }
}
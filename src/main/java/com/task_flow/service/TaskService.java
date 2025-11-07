package com.task_flow.service;

import com.task_flow.dto.TaskRequestDTO;
import com.task_flow.dto.TaskResponseDTO;
import com.task_flow.exception.ProjectNotFoundException;
import com.task_flow.exception.TaskNotFoundException;
import com.task_flow.exception.UserNotFoundException;
import com.task_flow.model.Project;
import com.task_flow.model.Status;
import com.task_flow.model.Task;
import com.task_flow.model.User;
import com.task_flow.repository.ProjectRepository;
import com.task_flow.repository.TaskRepository;
import com.task_flow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    //Metodo que cria uma tarefa
    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        Task task = new Task();
        task.setTitle(taskRequestDTO.title());
        task.setDescription(taskRequestDTO.description());
        task.setStatus(taskRequestDTO.status());
        task.setPriority(taskRequestDTO.priority());
        task.setDueDate(taskRequestDTO.dueDate());

        User assignee = findUserById(taskRequestDTO.assigneeId());
        task.setAssignee(assignee);

        if (taskRequestDTO.projectId() != null) {
            Project project = findProjectById(taskRequestDTO.projectId());
            task.setProject(project);
        }

        Task savedTask = taskRepository.save(task);
        return convertToDto(savedTask);
    }

    //Metodo que lista todas as tarefas
    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    //Metodo que lista uma tarefa pelo ID
    public TaskResponseDTO getTaskById(Long id) {
        Task task = findTaskById(id);
        return convertToDto(task);
    }

    //Metodo que atualiza uma tarefa
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO taskRequestDTO) {
        Task existingTask = findTaskById(id);

        existingTask.setTitle(taskRequestDTO.title());
        existingTask.setDescription(taskRequestDTO.description());
        existingTask.setStatus(taskRequestDTO.status());
        existingTask.setPriority(taskRequestDTO.priority());
        existingTask.setDueDate(taskRequestDTO.dueDate());

        if (taskRequestDTO.assigneeId() != null) {
            User assignee = findUserById(taskRequestDTO.assigneeId());
            existingTask.setAssignee(assignee);
        } else {
            existingTask.setAssignee(null);
        }

        if (taskRequestDTO.projectId() != null) {
            Project project = findProjectById(taskRequestDTO.projectId());
            existingTask.setProject(project);
        } else {
            existingTask.setProject(null);
        }

        User currentUser = getCurrentAuthenticatedUser();
        existingTask.setLastModifiedBy(currentUser);

        Task updatedTask = taskRepository.save(existingTask);
        return convertToDto(updatedTask);
    }

    //Metodo que deleta uma tarefa
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    //Metodo que lista a quantidade de tarefas por status
    public Map<Status, Long> getTaskCountByStatus() {
        Map<Status, Long> taskCounts = new HashMap<>();
        for (Status status : Status.values()) {
            taskCounts.put(status, taskRepository.countByStatus(status));
        }
        return taskCounts;
    }

    //Metodo que lista as tarefas de um usuario
    public List<TaskResponseDTO> getTasksByAssignee(Long assigneeId) {
        User assignee = findUserById(assigneeId);
        return taskRepository.findByAssignee(assignee).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    //Metodo que lista as tarefas de um projeto
    public List<TaskResponseDTO> getTasksByProjectId(Long projectId) {
        return taskRepository.findByProjectId(projectId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    //Metodo que lista um usuario pelo ID
    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    //Metodo que lista um projeto pelo ID
    private Project findProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with ID: " + projectId));
    }

    //Metodo que lista uma tarefa pelo ID
    private Task findTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + taskId));
    }

    //Metodo que lista o usuario autenticado
    private User getCurrentAuthenticatedUser() {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("Authenticated user not found"));
    }

    //Metodo que converte uma tarefa em DTO
    private TaskResponseDTO convertToDto(Task task) {
        Long assigneeId = null;
        String assigneeUsername = null;
        if (task.getAssignee() != null) {
            assigneeId = task.getAssignee().getId();
            assigneeUsername = task.getAssignee().getUsername();
        }

        Long projectId = null;
        String projectName = null;
        if (task.getProject() != null) {
            projectId = task.getProject().getId();
            projectName = task.getProject().getName();
        }

        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                assigneeId,
                assigneeUsername,
                projectId,
                projectName
        );
    }
}
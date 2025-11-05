package com.task_flow.service;

import com.task_flow.dto.CommentRequestDTO;
import com.task_flow.dto.CommentResponseDTO;
import com.task_flow.model.Comment;
import com.task_flow.model.Task;
import com.task_flow.model.User;
import com.task_flow.repository.CommentRepository;
import com.task_flow.repository.TaskRepository;
import com.task_flow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO) {
        Task task = taskRepository.findById(commentRequestDTO.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + commentRequestDTO.getTaskId()));
        User user = userRepository.findById(commentRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + commentRequestDTO.getUserId()));

        Comment comment = new Comment();
        comment.setContent(commentRequestDTO.getContent());
        comment.setTask(task);
        comment.setUser(user);

        Comment savedComment = commentRepository.save(comment);
        return convertToDto(savedComment);
    }

    public List<CommentResponseDTO> getCommentsByTaskId(Long taskId) {
        return commentRepository.findByTaskId(taskId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    private CommentResponseDTO convertToDto(Comment comment) {
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setTaskId(comment.getTask().getId());
        dto.setUserId(comment.getUser().getId());
        dto.setUsername(comment.getUser().getUsername());
        dto.setCreatedAt(comment.getCreatedAt());
        return dto;
    }
}

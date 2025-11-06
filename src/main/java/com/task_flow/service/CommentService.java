package com.task_flow.service;

import com.task_flow.dto.CommentRequestDTO;
import com.task_flow.dto.CommentResponseDTO;
import com.task_flow.exception.TaskNotFoundException;
import com.task_flow.exception.UserNotFoundException;
import com.task_flow.model.Comment;
import com.task_flow.model.Task;
import com.task_flow.model.User;
import com.task_flow.repository.CommentRepository;
import com.task_flow.repository.TaskRepository;
import com.task_flow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO) {
        Task task = taskRepository.findById(commentRequestDTO.taskId())
                .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + commentRequestDTO.taskId()));
        User user = userRepository.findById(commentRequestDTO.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + commentRequestDTO.userId()));

        Comment comment = new Comment();
        comment.setContent(commentRequestDTO.content());
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
        return new CommentResponseDTO(
                comment.getId(),
                comment.getContent(),
                comment.getTask().getId(),
                comment.getUser().getId(),
                comment.getUser().getUsername(),
                comment.getCreatedAt()
        );
    }
}

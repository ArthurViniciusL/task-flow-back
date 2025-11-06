package com.task_flow.controller;

import com.task_flow.dto.CommentRequestDTO;
import com.task_flow.dto.CommentResponseDTO;
import com.task_flow.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO createdComment = commentService.createComment(commentRequestDTO);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByTaskId(@PathVariable Long taskId) {
        List<CommentResponseDTO> comments = commentService.getCommentsByTaskId(taskId);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

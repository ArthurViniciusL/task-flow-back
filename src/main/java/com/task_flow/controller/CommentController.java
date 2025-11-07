package com.task_flow.controller;

import com.task_flow.dto.CommentRequestDTO;
import com.task_flow.dto.CommentResponseDTO;
import com.task_flow.service.CommentService;
import com.task_flow.annotation.ApiResponseDocumentation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Tag(name = "Comentários", description = "API de gerenciamento de comentários em tarefas")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Criar novo comentário", description = "Adiciona um comentário a uma tarefa")
    @ApiResponsePost
    @PostMapping
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO createdComment = commentService.createComment(commentRequestDTO);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar comentários de uma tarefa", description = "Retorna todos os comentários associados a uma tarefa específica")
    @ApiResponseGetList
    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByTaskId(@PathVariable Long taskId) {
        List<CommentResponseDTO> comments = commentService.getCommentsByTaskId(taskId);
        return ResponseEntity.ok(comments);
    }

    @Operation(summary = "Excluir comentário", description = "Remove um comentário do sistema")
    @ApiResponseDelete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
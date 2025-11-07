package com.task_flow.task_flow_app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task_flow.controller.CommentController;
import com.task_flow.dto.CommentRequestDTO;
import com.task_flow.dto.CommentResponseDTO;
import com.task_flow.service.CommentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Testes do CommentController (MockMvc standalone + Mockito)")
class CommentControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    private CommentRequestDTO request;
    private CommentResponseDTO response;

    @BeforeEach
    @DisplayName("Configuração inicial")
    void setup() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();

        // ORDEM CERTA: content, taskId, userId
        request = new CommentRequestDTO(
                "Test comment content",
                1L,
                1L
        );

        // ORDEM CERTA DO CommentResponseDTO:
        // (id, content, taskId, userId, username, createdAt)
        response = new CommentResponseDTO(
                1L,
                "Test comment content",
                1L,
                1L,
                "testuser",
                LocalDateTime.now()
        );
    }

    @Test
    @DisplayName("Deve criar um comentário com sucesso")
    void createComment_success() throws Exception {
        when(commentService.createComment(any())).thenReturn(response);

        mockMvc.perform(
                        post("/api/comments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(response.id()))
                .andExpect(jsonPath("$.content").value(response.content()));

        verify(commentService).createComment(any());
    }

    @Test
    @DisplayName("Deve listar comentários por taskId")
    void getCommentsByTaskId_success() throws Exception {

        List<CommentResponseDTO> list = List.of(
                response,
                new CommentResponseDTO(
                        2L,
                        "Another comment",
                        1L,
                        2L,
                        "otherUser",
                        LocalDateTime.now()
                )
        );

        when(commentService.getCommentsByTaskId(1L)).thenReturn(list);

        mockMvc.perform(get("/api/comments/task/{taskId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(list.get(0).id()))
                .andExpect(jsonPath("$[1].id").value(list.get(1).id()));

        verify(commentService).getCommentsByTaskId(1L);
    }

    @Test
    @DisplayName("Deve excluir um comentário pelo ID")
    void deleteComment_success() throws Exception {
        doNothing().when(commentService).deleteComment(1L);

        mockMvc.perform(delete("/api/comments/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(commentService).deleteComment(1L);
    }
}

package com.task_flow.controller;

import com.task_flow.dto.NotificationResponseDTO;
import com.task_flow.service.NotificationService;
import com.task_flow.annotation.ApiResponseDocumentation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Tag(name = "Notificações", description = "API de gerenciamento de notificações de usuários")
public class NotificationController {

    private final NotificationService notificationService;

    @Operation(summary = "Listar notificações do usuário", description = "Retorna todas as notificações de um usuário específico")
    @ApiResponseGetList
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationResponseDTO>> getNotificationsForUser(@PathVariable Long userId) {
        List<NotificationResponseDTO> notifications = notificationService.getNotificationsForUser(userId);
        return ResponseEntity.ok(notifications);
    }

    @Operation(summary = "Listar notificações não lidas", description = "Retorna todas as notificações não lidas de um usuário específico")
    @ApiResponseGetList
    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<NotificationResponseDTO>> getUnreadNotificationsForUser(@PathVariable Long userId) {
        List<NotificationResponseDTO> notifications = notificationService.getUnreadNotificationsForUser(userId);
        return ResponseEntity.ok(notifications);
    }

    @Operation(summary = "Marcar notificação como lida", description = "Atualiza o status de uma notificação para lida")
    @ApiResponsePut
    @PutMapping("/{notificationId}/read")
    public ResponseEntity<NotificationResponseDTO> markNotificationAsRead(@PathVariable Long notificationId) {
        NotificationResponseDTO updatedNotification = notificationService.markNotificationAsRead(notificationId);
        return ResponseEntity.ok(updatedNotification);
    }

    @Operation(summary = "Excluir notificação", description = "Remove uma notificação do sistema")
    @ApiResponseDelete
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long notificationId) {
        notificationService.deleteNotification(notificationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
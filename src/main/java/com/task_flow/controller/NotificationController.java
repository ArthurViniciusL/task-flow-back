package com.task_flow.controller;

import com.task_flow.dto.NotificationResponseDTO;
import com.task_flow.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationResponseDTO>> getNotificationsForUser(@PathVariable Long userId) {
        List<NotificationResponseDTO> notifications = notificationService.getNotificationsForUser(userId);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<NotificationResponseDTO>> getUnreadNotificationsForUser(@PathVariable Long userId) {
        List<NotificationResponseDTO> notifications = notificationService.getUnreadNotificationsForUser(userId);
        return ResponseEntity.ok(notifications);
    }

    @PutMapping("/{notificationId}/read")
    public ResponseEntity<NotificationResponseDTO> markNotificationAsRead(@PathVariable Long notificationId) {
        NotificationResponseDTO updatedNotification = notificationService.markNotificationAsRead(notificationId);
        return ResponseEntity.ok(updatedNotification);
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long notificationId) {
        notificationService.deleteNotification(notificationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

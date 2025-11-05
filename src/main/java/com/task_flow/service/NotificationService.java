package com.task_flow.service;

import com.task_flow.dto.NotificationResponseDTO;
import com.task_flow.model.Notification;
import com.task_flow.model.User;
import com.task_flow.repository.NotificationRepository;
import com.task_flow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public NotificationResponseDTO createNotification(Long userId, String message) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setRead(false); // New notifications are unread by default

        Notification savedNotification = notificationRepository.save(notification);
        return convertToDto(savedNotification);
    }

    public List<NotificationResponseDTO> getNotificationsForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        return notificationRepository.findByUserOrderByCreatedAtDesc(user).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<NotificationResponseDTO> getUnreadNotificationsForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        return notificationRepository.findByUserAndReadFalseOrderByCreatedAtDesc(user).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public NotificationResponseDTO markNotificationAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found with ID: " + notificationId));
        notification.setRead(true);
        Notification updatedNotification = notificationRepository.save(notification);
        return convertToDto(updatedNotification);
    }

    public void deleteNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }

    private NotificationResponseDTO convertToDto(Notification notification) {
        NotificationResponseDTO dto = new NotificationResponseDTO();
        dto.setId(notification.getId());
        dto.setMessage(notification.getMessage());
        dto.setRead(notification.isRead());
        dto.setCreatedAt(notification.getCreatedAt());
        dto.setUserId(notification.getUser().getId());
        dto.setUsername(notification.getUser().getUsername());
        return dto;
    }
}

package com.task_flow.service;

import com.task_flow.dto.NotificationResponseDTO;
import com.task_flow.exception.NotificationNotFoundException;
import com.task_flow.exception.UserNotFoundException;
import com.task_flow.model.Notification;
import com.task_flow.model.User;
import com.task_flow.repository.NotificationRepository;
import com.task_flow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationResponseDTO createNotification(Long userId, String message) {
        User user = findUserById(userId);

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setRead(false); // New notifications are unread by default

        Notification savedNotification = notificationRepository.save(notification);
        return convertToDto(savedNotification);
    }

    public List<NotificationResponseDTO> getNotificationsForUser(Long userId) {
        User user = findUserById(userId);
        return notificationRepository.findByUserOrderByCreatedAtDesc(user).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<NotificationResponseDTO> getUnreadNotificationsForUser(Long userId) {
        User user = findUserById(userId);
        return notificationRepository.findByUserAndReadFalseOrderByCreatedAtDesc(user).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public NotificationResponseDTO markNotificationAsRead(Long notificationId) {
        Notification notification = findNotificationById(notificationId);
        notification.setRead(true);
        Notification updatedNotification = notificationRepository.save(notification);
        return convertToDto(updatedNotification);
    }

    public void deleteNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    private Notification findNotificationById(Long notificationId) {
        return notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found with ID: " + notificationId));
    }

    private NotificationResponseDTO convertToDto(Notification notification) {
        return new NotificationResponseDTO(
                notification.getId(),
                notification.getMessage(),
                notification.isRead(),
                notification.getCreatedAt(),
                notification.getUser().getId(),
                notification.getUser().getUsername()
        );
    }
}

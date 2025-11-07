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

    //Metodo que cria uma notificacao
    public NotificationResponseDTO createNotification(Long userId, String message) {
        User user = findUserById(userId);

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setRead(false); // New notifications are unread by default

        Notification savedNotification = notificationRepository.save(notification);
        return convertToDto(savedNotification);
    }

    //Metodo que lista todas as notificacoes de um usuario
    public List<NotificationResponseDTO> getNotificationsForUser(Long userId) {
        User user = findUserById(userId);
        return notificationRepository.findByUserOrderByCreatedAtDesc(user).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    //Metodo que lista todas as notificacoes nao lidas de um usuario
    public List<NotificationResponseDTO> getUnreadNotificationsForUser(Long userId) {
        User user = findUserById(userId);
        return notificationRepository.findByUserAndIsReadFalseOrderByCreatedAtDesc(user).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    //Metodo que marca uma notificacao como lida
    public NotificationResponseDTO markNotificationAsRead(Long notificationId) {
        Notification notification = findNotificationById(notificationId);
        notification.setRead(true);
        Notification updatedNotification = notificationRepository.save(notification);
        return convertToDto(updatedNotification);
    }

    //Metodo que deleta uma notificacao
    public void deleteNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }

    //Metodo que lista um usário pelo ID
    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    //Metodo que lista uma notificacao pelo ID
    private Notification findNotificationById(Long notificationId) {
        return notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found with ID: " + notificationId));
    }

    //Metodo que converte uma notificacao em DTO
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

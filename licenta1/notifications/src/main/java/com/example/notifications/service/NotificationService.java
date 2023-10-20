package com.example.notifications.service;

import com.example.clients.notification.NotificationRequest;
import com.example.notifications.entity.Notification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.notifications.repository.NotificationRepository;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .toUserId(notificationRequest.toUserId())
                        .toUsername(notificationRequest.toUsername())
                        .sender("Photogram")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }
}

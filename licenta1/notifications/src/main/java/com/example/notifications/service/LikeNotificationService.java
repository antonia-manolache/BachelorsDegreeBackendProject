package com.example.notifications.service;

import com.example.clients.like.LikeNotificationRequest;
import com.example.notifications.entity.LikeNotification;
import com.example.notifications.repository.LikeNotificationRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class LikeNotificationService {
    private final LikeNotificationRepository notificationRepository;

    public void send(LikeNotificationRequest notificationRequest) {
        notificationRepository.save(
                LikeNotification.builder()
                        .byUserId(notificationRequest.byUserId())
                        .toPostId(notificationRequest.toPostId())
                        .sender("Photogram")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }
}

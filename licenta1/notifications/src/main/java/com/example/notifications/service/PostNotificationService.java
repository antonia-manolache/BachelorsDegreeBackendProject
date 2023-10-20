package com.example.notifications.service;

import com.example.clients.post.AddPostRequest;
import com.example.notifications.entity.PostNotification;
import com.example.notifications.repository.PostNotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PostNotificationService {
    private final PostNotificationRepository notificationRepository;

    public void send(AddPostRequest notificationRequest) {
        notificationRepository.save(
                PostNotification.builder()
                        .userId(notificationRequest.userId())
                        .objectUrl(notificationRequest.objectUrl())
                        .sender("Photogram")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }
}

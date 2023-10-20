package com.example.notifications.controller;

import com.example.clients.like.LikeNotificationRequest;
import com.example.clients.post.AddPostRequest;
import com.example.notifications.service.LikeNotificationService;
import com.example.notifications.service.PostNotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/like-notification")
@AllArgsConstructor
@Slf4j
public class LikeNotificationController{
    private final LikeNotificationService notificationService;

    @PostMapping
    public void sendLikeNotification(@RequestBody LikeNotificationRequest notificationRequest) {
        log.info("New notification {}", notificationRequest);
        notificationService.send(notificationRequest);
    }
}

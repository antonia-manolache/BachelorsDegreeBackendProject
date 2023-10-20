package com.example.notifications.controller;

import com.example.clients.post.AddPostRequest;
import com.example.notifications.service.PostNotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/post-notification")
@AllArgsConstructor
@Slf4j
public class PostNotificationController {
    private final PostNotificationService notificationService;

    @PostMapping
    public void sendPostNotification(@RequestBody AddPostRequest notificationRequest) {
        log.info("New notification {}", notificationRequest);
        notificationService.send(notificationRequest);
    }
}

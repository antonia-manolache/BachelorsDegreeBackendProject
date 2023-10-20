package com.example.clients.comment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "comment-notification",
        url = "${clients.notification.url}"
)
public interface CommentClient {
    @PostMapping("api/v1/comment-notification")
    void sendNotification(CommentNotificationRequest notificationRequest);
}

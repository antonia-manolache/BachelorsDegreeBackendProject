package com.example.clients.like;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "like-notification",
        url = "${clients.notification.url}"
)
public interface LikeClient {
    @PostMapping("api/v1/like-notification")
    void sendNotification(LikeNotificationRequest notificationRequest);
}

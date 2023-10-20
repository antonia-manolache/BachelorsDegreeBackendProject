package com.example.clients.post;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "post-notification",
        url = "${clients.notification.url}"
)
public interface PostClient {
    @PostMapping("api/v1/post-notification")
    void sendNotification(AddPostRequest notificationRequest);
}

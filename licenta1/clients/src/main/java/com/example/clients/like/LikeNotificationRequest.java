package com.example.clients.like;

public record LikeNotificationRequest(
        String byUserId,
        int toPostId,
        String message
) {
}

package com.example.clients.comment;

public record CommentNotificationRequest(
        String byUserId,
        String toPostId,
        String message
) {
}

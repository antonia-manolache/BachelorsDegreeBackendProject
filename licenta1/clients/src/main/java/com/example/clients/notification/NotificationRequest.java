package com.example.clients.notification;

public record NotificationRequest(
        String toUserId,
        String toUsername,
        String message
) {
}

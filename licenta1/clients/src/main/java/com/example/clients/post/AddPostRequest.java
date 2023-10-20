package com.example.clients.post;

public record AddPostRequest(
        String userId,
        String objectUrl,
        String message
) {
}

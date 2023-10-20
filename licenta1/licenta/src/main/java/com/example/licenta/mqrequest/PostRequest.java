package com.example.licenta.mqrequest;

public record PostRequest(
        String fileName,
        String objectUrl,
        String version,
        String userId,
        String username,
        String description,
        int challengeId
) {
}

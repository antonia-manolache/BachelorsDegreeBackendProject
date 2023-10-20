package com.example.comment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@FeignClient(name = "user-service", url="${application.config.users-url}")
public interface UserClient {
    @GetMapping("/get-user/{userId}")
    User findUserByUserId(@PathVariable("userId") String userId);
}

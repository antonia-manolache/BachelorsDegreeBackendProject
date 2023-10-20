package com.example.notifications.repository;

import com.example.notifications.entity.Notification;
import com.example.notifications.entity.PostNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostNotificationRepository extends JpaRepository<PostNotification, Integer> {
}

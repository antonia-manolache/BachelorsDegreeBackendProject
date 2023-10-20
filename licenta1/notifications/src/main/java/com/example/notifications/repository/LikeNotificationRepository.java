package com.example.notifications.repository;

import com.example.notifications.entity.LikeNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeNotificationRepository extends JpaRepository<LikeNotification, Integer> {
}

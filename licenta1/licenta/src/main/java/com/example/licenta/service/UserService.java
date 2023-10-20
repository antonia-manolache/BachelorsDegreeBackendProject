package com.example.licenta.service;

import com.example.amqp.RabbitMQMessageProducer;
import com.example.clients.notification.NotificationRequest;
import com.example.licenta.entity.UserEntity;
import com.example.licenta.mqrequest.UserRegistrationRequest;
import com.example.licenta.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public UserEntity register(UserEntity user){
        NotificationRequest notificationRequest = new NotificationRequest(
                user.getUserId(),
                user.getUsername(),
                String.format("Account created for: ",
                        user.getName())
        );
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
        return userRepository.save(user);
    }

    public UserEntity findUserByUserId(String userId){return userRepository.findByUserId(userId);}

    public UserEntity setSolved(String userId){
        UserEntity user = userRepository.findByUserId(userId);
        user.setSolved(user.getSolved()+1);
        return userRepository.save(user);
    }
}

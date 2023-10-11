package com.example.licenta.service;

import com.example.licenta.entity.UserEntity;
import com.example.licenta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserEntity register(UserEntity user){
        return userRepository.save(user);
    }
}

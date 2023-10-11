package com.example.licenta.repository;

import com.example.licenta.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Boolean existsByUsername(String username);
    UserEntity findByUsername(String email);

    Boolean existsByUserId(String userId);
    UserEntity findByUserId(String userID);

    void deleteByUserId(String userId);




}

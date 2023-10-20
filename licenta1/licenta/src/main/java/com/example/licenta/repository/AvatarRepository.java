package com.example.licenta.repository;

import com.example.licenta.entity.AvatarEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvatarRepository extends CrudRepository<AvatarEntity,Integer> {
    AvatarEntity findByUserId(String userId);




}
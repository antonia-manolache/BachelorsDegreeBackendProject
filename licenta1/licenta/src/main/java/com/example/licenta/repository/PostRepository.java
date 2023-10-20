package com.example.licenta.repository;

import com.example.licenta.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<PostEntity,Integer> {
    List<PostEntity> findAll();
    List<PostEntity> findAllByUserIdOrderByIdDesc(String userId);
    List<PostEntity> findAllByChallengeIdOrderByIdDesc(int challengeId);
    List<PostEntity> findAllByOrderByIdDesc();
    PostEntity findById(int postId);


}

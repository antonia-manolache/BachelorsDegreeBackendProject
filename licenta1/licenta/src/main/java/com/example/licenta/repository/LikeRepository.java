package com.example.licenta.repository;

import com.example.licenta.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Integer> {
    LikeEntity findByUserIdAndPostId(String userId, int postId);

    @Query("SELECT COUNT(p) FROM LikeEntity p WHERE p.postId = :postId")
    int countByPostId(int postId);
}

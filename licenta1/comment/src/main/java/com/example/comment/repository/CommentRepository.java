package com.example.comment.repository;

import com.example.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    CommentEntity save(CommentEntity comment);
    ArrayList<CommentEntity> findAllByPostId(String postId);
    ArrayList<CommentEntity> findAll();
}

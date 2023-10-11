package com.example.licenta.service;

import com.amazonaws.services.s3.model.S3Object;
import com.example.licenta.entity.PostEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    public void upload(MultipartFile file,String userId, int likes) throws IOException;
    public List<PostEntity> list();

    public List<PostEntity> getAllPosts();
    public List<PostEntity> getAllPostsByUserId(String userId);
}
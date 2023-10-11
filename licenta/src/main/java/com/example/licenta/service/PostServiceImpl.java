package com.example.licenta.service;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.example.licenta.entity.PostEntity;
import com.example.licenta.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class PostServiceImpl implements PostService{
    @Autowired
    private AmazonService amazonService;

    @Autowired
    private PostRepository postRepository;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    @Override
    public void upload(MultipartFile file,String userId, int likes) throws IOException {

        if (file.isEmpty())
            throw new IllegalStateException("Cannot upload empty file");

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", bucketName, UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());

        // Uploading file to s3
        PutObjectResult putObjectResult = amazonService.upload(
                path, fileName, Optional.of(metadata), file.getInputStream());

        String objectUrl = amazonService.getUrl(bucketName, path,fileName).toString();

        // Saving metadata to db
        postRepository.save(new PostEntity(fileName, objectUrl, putObjectResult.getMetadata().getVersionId(),userId,likes));

    }



    @Override
    public List<PostEntity> list() {
        List<PostEntity> metas = new ArrayList<>();
        postRepository.findAll().forEach(metas::add);
        return metas;
    }

    @Override
    public List<PostEntity> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<PostEntity> getAllPostsByUserId(String userId) {
        return postRepository.findAllByUserId(userId);
    }


}

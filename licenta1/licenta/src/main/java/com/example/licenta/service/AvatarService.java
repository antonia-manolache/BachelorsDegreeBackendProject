package com.example.licenta.service;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.example.licenta.entity.AvatarEntity;
import com.example.licenta.entity.PostEntity;
import com.example.licenta.entity.UserEntity;
import com.example.licenta.repository.AvatarRepository;
import com.example.licenta.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class AvatarService {
    @Autowired
    private AmazonService amazonService;

    @Autowired
    private AvatarRepository avatarRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;


    public void upload(MultipartFile file, String userId) throws IOException {

        if (file.isEmpty())
            throw new IllegalStateException("Cannot upload empty file");

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String amazonId = String.format("%s", UUID.randomUUID());
        String path = String.format("%s/%s", bucketName, amazonId);

        String fileName = String.format("%s", file.getOriginalFilename());

        // Uploading file to s3
        PutObjectResult putObjectResult = amazonService.upload(
                path, fileName, Optional.of(metadata), file.getInputStream());

        String objectUrl = amazonService.getUrl(bucketName, amazonId,fileName).toString();

        UserEntity user = userRepository.findByUserId(userId);

        // Saving metadata to db
        avatarRepository.save(new AvatarEntity(fileName, objectUrl, putObjectResult.getMetadata().getVersionId(),userId));

    }

    public AvatarEntity getAvatarByUserId(String userId) {
        return avatarRepository.findByUserId(userId);
    }
}

package com.example.licenta.service;

import com.amazonaws.services.s3.model.PutObjectResult;

import com.example.clients.post.AddPostRequest;
import com.example.licenta.entity.PostEntity;
import com.example.licenta.entity.UserEntity;
import com.example.licenta.repository.PostRepository;
import com.example.licenta.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.amqp.RabbitMQMessageProducer;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class PostServiceImpl implements PostService{
    @Autowired
    private  RabbitMQMessageProducer rabbitMQMessageProducer;
    @Autowired
    private AmazonService amazonService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;


    @Override
    public void upload(MultipartFile file,String userId, String description, int challengeId) throws IOException {

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
        postRepository.save(new PostEntity(fileName, objectUrl, putObjectResult.getMetadata().getVersionId(),userId, user.getUsername(),description, challengeId));

        userService.setSolved(userId);
        AddPostRequest notificationRequest = new AddPostRequest(
                userId,
                objectUrl,
                String.format("Post added by ",
                        user.getUserId())
        );
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
    }



    @Override
    public List<PostEntity> list() {
        List<PostEntity> metas = new ArrayList<>();
        postRepository.findAllByOrderByIdDesc().forEach(metas::add);
        return metas;
    }

    @Override
    public List<PostEntity> getAllPosts() {
        return postRepository.findAllByOrderByIdDesc();
    }

    @Override
    public List<PostEntity> getAllPostsByUserId(String userId) {
        return postRepository.findAllByUserIdOrderByIdDesc(userId);
    }

    @Override
    public List<PostEntity> getAllPostsByChallengeId(int challengeId) {
        return postRepository.findAllByChallengeIdOrderByIdDesc(challengeId);
    }



}

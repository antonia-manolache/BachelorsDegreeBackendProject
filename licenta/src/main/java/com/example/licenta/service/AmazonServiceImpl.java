package com.example.licenta.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import software.amazon.awssdk.services.s3.S3Client;
import com.example.licenta.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
@Service
@Slf4j
public class AmazonServiceImpl implements AmazonService{
    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private S3Client s3Client;

    @Autowired
    private PostRepository postRepository;

    @Override
    public PutObjectResult upload(
            String path,
            String fileName,
            Optional<Map<String, String>> optionalMetaData,
            InputStream inputStream) {
        ObjectMetadata objectMetadata = new ObjectMetadata();

        optionalMetaData.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach(objectMetadata::addUserMetadata);
            }
        });
        log.debug("Path: " + path + ", FileName:" + fileName);
        return amazonS3.putObject(path, fileName, inputStream, objectMetadata);
    }

    public S3Object download(String path, String fileName) {
        return amazonS3.getObject(path, fileName);
    }

    public String getUrl(String bucketName,String path, String fileName) {
        String objectUrl = amazonS3.getUrl(bucketName, path + "/" + fileName).toString();
        return objectUrl;
    }

}

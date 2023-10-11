package com.example.licenta.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AmazonConfig {
    @Value("${aws.access.key.id}")
    private String accessKey;
    @Value("${aws.secret.access.key}")
    private String secretKey;
    @Value("${aws.s3.region}")
    private String region;

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    @Bean
    public S3Client s3c() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(accessKey, secretKey);
        return S3Client.builder()
                .region(Region.EU_NORTH_1)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }
}
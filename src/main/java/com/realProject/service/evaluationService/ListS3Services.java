package com.realProject.service.evaluationService;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ListS3Services {

        private S3Client s3Client;

        @Value("${aws.credentials.access-key}")
        private String accessKey;

        @Value("${aws.credentials.secret-key}")
        private String secretKey;

        @Value("${aws.region}")
        private String region;

        @Value("${aws.s3.bucket-name}")
        private String bucketName;

        @PostConstruct
        public void init() {
            this.s3Client = S3Client.builder()
                    .region(Region.of(region))
                    .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                    .build();
        }

        public List<String> uploadFiles(List<MultipartFile> files) {
            List<String> fileUrls = new ArrayList<>();
            for (MultipartFile file : files) {
                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                try {
                    s3Client.putObject(
                            PutObjectRequest.builder()
                                    .bucket(bucketName)
                                    .key(fileName)
                                    .build(),
                            RequestBody.fromByteBuffer(ByteBuffer.wrap(file.getBytes()))
                    );
                    fileUrls.add("https://" + bucketName + ".s3.amazonaws.com/" + fileName);
                } catch (IOException e) {
                    throw new RuntimeException("Error uploading file: " + fileName, e);
                }
            }
            return fileUrls;
        }
    }

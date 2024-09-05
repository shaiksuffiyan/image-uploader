package com.example.image_uploader.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName; // Reads the bucket name from application.properties

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client; // Inject the configured S3 client
    }

    public String uploadFile(MultipartFile file) throws IOException {
        // Get the file name from the uploaded file (from the user's local system)
        String fileName = file.getOriginalFilename();

        // Create a request to upload the file to S3
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName) // Use the bucket name from application.properties
                .key(fileName) // S3 object key (i.e., the file name in S3)
                .build();

        // Upload the file directly from the MultipartFile's input stream
        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        return "File uploaded successfully: " + fileName;
    }
}

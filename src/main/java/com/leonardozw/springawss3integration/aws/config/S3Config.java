package com.leonardozw.springawss3integration.aws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {
    
    @Bean
    public S3Client s3Client() {
        AwsCredentialsProvider credentialsProvider = DefaultCredentialsProvider.create();
        return S3Client.builder()
                .credentialsProvider(credentialsProvider)
                .build();
    }
}

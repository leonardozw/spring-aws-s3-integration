package com.leonardozw.springawss3integration.aws;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class AwsS3Service {
    
    private final S3Client s3Client;

    public AwsS3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadImage(MultipartFile file, UUID id, String bucketName) throws IOException {

        String key = UUID.randomUUID() + "-" + file.getOriginalFilename();
        byte[] fileBytes = file.getBytes();
        String mimeType = new Tika().detect(fileBytes);

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", mimeType);
        metadata.put("AuthorId", id.toString());

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .metadata(metadata)
                .build();

        try {
            s3Client.putObject(request, RequestBody.fromBytes(fileBytes));
            return "https://" + bucketName + ".s3.amazonaws.com/" + key;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    /*public List<String> listImages(String bucketName) {
        try {
            List<String> keys = new ArrayList<>();

            ListObjectsV2Request request = ListObjectsV2Request.builder().bucket(bucketName).build();

            ListObjectsV2Response response = s3Client.listObjectsV2(request);

            for(S3Object s3Object : response.contents()){
                keys.add(s3Object.key());
            }
            return keys;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/
}

package com.ecom.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.ecom.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.Region;


import java.io.InputStream;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    public AmazonS3 amazonS3;

    @Value("${aws.s3.bucket.category}")
    private String categoryBucket;

    @Value("${aws.s3.buccket.product}")
    private String productBuccket;

    @Value("${aws.s3.buccket.profile}")
    private String profileBuccket;

    @Override
    public Boolean uploadFileS3(MultipartFile file, Integer bucketType) {
        String buccketName=null;
        try {


            if (bucketType == 1) {
                buccketName = categoryBucket;
            } else if (bucketType == 2) {
                buccketName = productBuccket;
            } else {
                buccketName = profileBuccket;
            }

            String filename = file.getOriginalFilename();

            InputStream inputStream = file.getInputStream();

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());

            PutObjectRequest putObjectRequest = new PutObjectRequest(buccketName, filename, inputStream,objectMetadata);
            PutObjectResult saveData = amazonS3.putObject(putObjectRequest);
            if (!ObjectUtils.isEmpty(saveData)){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}

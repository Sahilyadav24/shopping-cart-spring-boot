//package com.ecom.config;
//
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.s3.S3Client;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.*;
//
//
//@Configuration
//public class AwsConfig {
//
//    @Value("${aws.access.key}")
//    private String accessKey;
//
//    @Value("${aws.secret.access.key}")
//    private String secretKey;
//
//    @Value("${aws.region}")
//    private String region;
//
//    @Bean
//    public S3Client amazonS3() {
//        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
//        return S3Client.builder()
//                .region(Region.of(region)) // Ensure valid region name
//                .credentialsProvider(StaticCredentialsProvider.create(credentials))
//                .build();
//    }
//}

package com.ecom.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.regions.Regions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Value("${aws.access.key}")
    private String accessKey;

    @Value("${aws.secret.access.key}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    @Bean
    public AmazonS3 amazonS3() {
        // Use your actual AWS credentials and region
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3Client.builder()
                .withRegion(Regions.fromName(region)) // Use dynamic region based on properties
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}


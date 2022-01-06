package com.techprimers.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class AwsConfiguration {
    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;
    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(build());
    }

    private AmazonDynamoDB build() {
        String serviceEndpoint = String.format("dynamodb.%s.amazonaws.com", region);
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(
                serviceEndpoint,
                region);
        AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(accessKey, secretKey)
        );
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(credentialsProvider)
                .build();
    }
}
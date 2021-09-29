package com.jdbank.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;




@Configuration
public class StorageConfig {
	
	
	 String accessKey = "AKIAYORZ4ILUAMXKY34X";


	 String accessSecret = "wozV9cdCXA4sF9kV2pBQpyfBbTRHnTwaNQNffM4y";


	 String region = "ap-south-1";
	
	 @Bean
	    public AmazonS3 s3Client() {
	        AWSCredentials credentials = new BasicAWSCredentials(accessKey,accessSecret);
	        return AmazonS3ClientBuilder.standard()
	                .withCredentials(new AWSStaticCredentialsProvider(credentials))
	                .withRegion(region).build();
	    }

}

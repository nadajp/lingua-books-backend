package com.lingua.market.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
@EntityScan("com.lingua.market")
@EnableJpaRepositories("com.lingua.market.persistence.dao")
@ComponentScan("com.lingua.market")
public class AppConfig {

    @Bean
    ModelMapper modelMapper() {
       return new ModelMapper();
    }

    @Bean
    AmazonS3 amazonS3() {
        String region = "us-east-1";

        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .build();
        return s3;
    }
}
   

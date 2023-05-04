package com.lingua.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EntityScan("com.lingua.market.model")
@ComponentScan("com.lingua.market")
@EnableJpaRepositories("com.lingua.market.repository")
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
       return new ModelMapper();
    }
}

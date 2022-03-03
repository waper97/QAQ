package com.example.nacosspringcloudexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class    NacosSpringCloudExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosSpringCloudExampleApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate (RestTemplateBuilder builder) {
        RestTemplate RestTemplate = builder.build();
       return RestTemplate;
    }

}

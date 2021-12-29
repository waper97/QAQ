package com.example.nacosspringcloudexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Properties;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosSpringCloudExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosSpringCloudExampleApplication.class, args);
    }

}

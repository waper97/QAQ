package com.example.pazs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.example.pazs.mapper")
public class PazsApplication {



    public static void main(String[] args) {
        SpringApplication.run(PazsApplication.class, args);
    }

}

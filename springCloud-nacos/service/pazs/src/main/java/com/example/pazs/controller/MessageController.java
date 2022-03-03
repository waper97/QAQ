package com.example.pazs.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MessageController
 * @Description TODO
 * @Author wangpeng
 * @Date 2021/12/29 18:09
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Value("${server.port}")
    private String port;
    @LoadBalanced
    @GetMapping("getMessage")
    public Object returnMessage() {
        return "this is pazs message+"+port;
    }

        
}

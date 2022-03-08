package com.example.pazs.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Value("${server.port}")
    private String port;
    @LoadBalanced
    @GetMapping("getMessage")
    @ResponseBody
    public Object returnMessage() {
        return "this is pazs message+"+port;
    }


    public Object rabbimqTest() {

        return null;
    }

    @GetMapping("hello")
    public Object hello () {

        return "hello";
    }
        
}

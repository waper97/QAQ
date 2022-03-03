package com.example.nacosspringcloudexample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MessageController
 * @Description TODO
 * @Author wangpeng
 * @Date 2021/12/29 18:09
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @GetMapping("getMessage")
    public Object returnMessage() {
        return "this is example message";
    }
}

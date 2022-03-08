package com.example.nacosspringcloudexample.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName MessageFeignClient
 * @Description TODO
 * @Author wangpeng
 * @Date 2022/3/8 22:44
 */
@FeignClient(name = "pazs")
public interface MessageFeign {

    @GetMapping("getHello")
    String getHello (@PathVariable String str);


    @GetMapping("getFuckYou")
    String getFuckYou ();
}

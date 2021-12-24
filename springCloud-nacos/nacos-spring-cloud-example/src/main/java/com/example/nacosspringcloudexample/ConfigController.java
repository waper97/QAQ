package com.example.nacosspringcloudexample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ConfigController
 * @Description TODO
 * @Author wangpeng
 * @Date 2021/12/20 17:57
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;
    @Value("${user}")
    private String user;
    @Value("${server.port}")
    private Integer port;
    @Value("${fuck}")
    private String fuck;

    @RequestMapping("/get")
    public boolean get() {
        System.out.println(user);
        System.out.println(port);
        System.out.println(fuck);
        return useLocalCache;
    }
}

package com.example.launcher;

import com.example.LaunchConstant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @ClassName BeforeStartSettings
 * @Description TODO
 * @Author wangpeng
 * @Date 2021/12/21 10:40
 */

@Configuration
@Component
@Order(value = 1)
public class BeforeStartSettings implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("====================启动前操作=======================");
        // 系统参数
        Properties properties = System.getProperties();
        String profile = (String) properties.get("profile");
        System.out.println(properties);
        properties.setProperty("spring.cloud.nacos.config.server-addr", LaunchConstant.nacosAddr(profile));
        properties.setProperty("spring.cloud.nacos.discovery.server-addr", LaunchConstant.nacosAddr(profile));

    }


}

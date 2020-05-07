package com.waper.shoppingcenter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义配置类
 * @ClassName MyConfig
 * @Description TODO
 * @Author wangpeng
 * springboot 2.x 使用 ： implements WebMvcConfigurer
 * @Date 2020/4/14 9:28
 */
@Configuration
public class MyConfig  implements WebMvcConfigurer {

    /**
     *允许跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","POST","PUT")
                .allowCredentials(true).maxAge(3600);
    }


}

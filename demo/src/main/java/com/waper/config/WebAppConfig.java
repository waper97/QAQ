package com.waper.config;

import com.waper.filter.UserInterceper;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

/**
 * create by ${user} on 2019/3/20
 * *
 **/
@Configuration//标注为配置类
public class WebAppConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new UserInterceper()).addPathPatterns("/**").excludePathPatterns("/login");
    }


    /**
     * addPathPatterns :添加规则，
     * excludePathPatterns：排除规则
     */
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new TestInterceptor());
//    }



    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("10MB");
        factory.setMaxRequestSize("10MB");
        return factory.createMultipartConfig();
    }

}

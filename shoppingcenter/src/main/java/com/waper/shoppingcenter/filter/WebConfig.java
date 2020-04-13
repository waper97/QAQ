package com.waper.shoppingcenter.filter;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @ClassName WebConfig
 * @Description TODO
 * @Author wangpeng
 * @Date 2020/4/10 17:17
 */
@Configuration
public class WebConfig  extends WebMvcConfigurationSupport {

    @Bean
    public ActionInterceptor actionInterceptor(){
        return new ActionInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        registry.addInterceptor(actionInterceptor())
        // 拦截
        .addPathPatterns("/**");
    }
}

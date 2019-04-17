package com.yj.common.config;

import java.util.Properties;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.druid.pool.DruidDataSource;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.yj.common.filter.ActionInterceptor;
/***
 *  实现WebMvcConfigurer中的方法
 * 	addFormatters ：增加格式化工具（用于接收参数）
 *	configureMessageConverters ：配置消息转换器（用于 @RequestBody 和 @ResponseBody ）
 *	configurePathMatch ：配置路径映射
 *	addArgumentResolvers ：配置参数解析器（用于接收参数）
 *	addInterceptors ：添加拦截器
 *
 * spring boot2.0 为 implements WebMvcConfigurer
 */
@Configuration
public class MyConfiguration extends WebMvcConfigurerAdapter {
	/**需要注入service时采用*/
	@Bean
	public ActionInterceptor actionInterceptor() {
		return new ActionInterceptor();
	}
	
	/**增加拦截器：addPathPatterns添加规则，excludePathPatterns排除规则*/
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(actionInterceptor())    //指定拦截器类
                .addPathPatterns("/**")
                .excludePathPatterns("/error","/kaptcha","/system/getParams",
                					 "/system/reset","/common/code/getVerifyCode",
                					 "/trace/supervise/getOrgBySchool",
                					 "/trace/supervise/getFoodByOrgid",
                					 "/trace/supervise/getTraceDetail"
                		);  //指定该类不拦截的url
    }
    
    /**允许跨域*/
    public void addCorsMappings(CorsRegistry registry) {
    	 registry.addMapping("/**")
         .allowedOrigins("*")
         .allowedMethods("GET","POST", "PUT","DELETE")
         .maxAge(3600)
         .allowCredentials(true);
    }
    
    
	/**统一配置类序列化（不返回值为NULL的字段）*/
	@Bean  
	@Primary  
	@ConditionalOnMissingBean(ObjectMapper.class)  
	public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder){  
	        // 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化  
	        // Include.ALWAYS 默认  
	        // Include.NON_DEFAULT 属性为默认值不序列化  
	        // Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化，则返回的json是没有这个字段的。这样对移动端会更省流量  
	        // Include.NON_NULL 属性为NULL 不序列化,就是为null的字段不参加序列化  
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();  
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return objectMapper;  
	}  
	
	/**
     * 设置上传文件大小，配置文件属性设置无效
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory config = new MultipartConfigFactory();
        config.setMaxFileSize("10MB");
        config.setMaxRequestSize("10MB");
        return config.createMultipartConfig();
    }
	
	/**定时器线程池（可同时执行多个任务）*/
	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler taskPool=new ThreadPoolTaskScheduler();
		taskPool.setPoolSize(10);
		taskPool.setThreadNamePrefix("定时任务");
		return taskPool;
	}
	
	
	/**分页配置:配置pageSizeZero=true时，PageHelper.startPage(1,0)表示查询全部*/
	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("offsetAsPageNum", "true");
		p.setProperty("rowBoundsWithCount", "true");
		p.setProperty("reasonable", "false");
		p.setProperty("pageSizeZero", "true");
		pageHelper.setProperties(p);
		return pageHelper;
	}
	
	/**阿里连接池配置*/
	@Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }
	
}

package com.yj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@SpringBootApplication注解等价于以默认属性使用@Configuration，@EnableAutoConfiguration和@ComponentScan（默认扫描@Controller和@Service）
@SpringBootApplication
@EnableTransactionManagement      // 如果mybatis中service实现类中加入事务注解，需要此处添加该注解
@MapperScan("com.yj.hqbz.mapper") // 扫描的是UserMapper.xml中namespace指向值的包位置
@ServletComponentScan             //扫描过滤器
@EnableScheduling                 //启动定时器
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

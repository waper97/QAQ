package com.waper.shoppingcenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(value = {"com.waper.shoppingcenter.dao"})
@EnableTransactionManagement
public class ShoppingcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingcenterApplication.class, args);
    }

}

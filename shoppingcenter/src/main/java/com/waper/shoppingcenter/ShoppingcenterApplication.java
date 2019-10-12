package com.waper.shoppingcenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.waper.shoppingcenter.dao"})
public class ShoppingcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingcenterApplication.class, args);
    }

}

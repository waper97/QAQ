package com.waper.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Users {
    private String id;

    private String username;

    private String password;

    private BigDecimal age;

    private String address;

    private Integer status;

}
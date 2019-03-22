package com.example.model;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class User {
    private String id;

    private String username;

    private String password;

    private BigDecimal age;

    private String address;


}
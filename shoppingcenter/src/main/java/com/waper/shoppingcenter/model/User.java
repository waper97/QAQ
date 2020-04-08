package com.waper.shoppingcenter.model;

import lombok.Data;

import java.util.Date;
@Data
public class User {
    private String id;

    private String address;

    private Date birthdate;

    private String mobile;

    private String name;

    private String password;

    private Integer role;

    private Integer status;

    private String username;
}
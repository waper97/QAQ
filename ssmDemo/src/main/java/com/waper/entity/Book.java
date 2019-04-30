package com.waper.entity;

import lombok.Data;

import java.util.Date;

/**
 * create by ${user} on 2019/4/30
 * *
 **/
@Data
public class Book {
    private String id;

    private String name;

    private Date publishDate;

    private Integer page;

}

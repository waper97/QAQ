package com.waper.shoppingcenter.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class Goods {
    private String id;

    private String name;

    private String goodsTypId;

    private Integer status;

    private String picture;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    private String createor;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatetime;

    private String updateor;

    private String updateorId;

    private String createorId;

    private String typeName;


}
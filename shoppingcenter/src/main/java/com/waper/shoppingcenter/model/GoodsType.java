package com.waper.shoppingcenter.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * create by  on 2019/5/21
 * *
 **/

@Data
public class GoodsType {

    private String id;

    private Integer typeId;

    private Integer parentId;

    private String name;

    private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String creator;

    private String creatorId;


}

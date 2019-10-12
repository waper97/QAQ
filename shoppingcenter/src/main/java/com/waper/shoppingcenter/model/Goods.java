package com.waper.shoppingcenter.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class Goods {

    private String id;

    private Date addTime;

    private String name;

    private String pircture;

    private Integer status;

    private String type;

    private Date updateTime;

    private Date addtime;

    private Date updatetime;


}

package com.waper.shoppingcenter.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * create by  on 2019/5/21
 * *
 **/

@Entity(name = "GOODSTYPE")
@Data
public class GoodsType {
    @Id
    @NonNull
    private String id;

    private Integer typeId;

    private Integer parentId;

    private String name;

    private Integer status;

    private Date addTime;

    private Date updateTime;

    private String creator;

    private String creatorId;

    public GoodsType() {
    }


}

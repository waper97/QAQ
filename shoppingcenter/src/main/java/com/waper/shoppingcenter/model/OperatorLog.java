package com.waper.shoppingcenter.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * create by  on 2019/5/22
 * *
 **/
@Entity(name = "OPERATOR_LOG")
@Data
public class OperatorLog {
    @Id
    private String id;

    private String  content;

    private String  addTime;

    private String creator;

    private String creatorId;
}

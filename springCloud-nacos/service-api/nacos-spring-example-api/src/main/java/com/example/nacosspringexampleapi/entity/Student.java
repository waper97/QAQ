package com.example.nacosspringexampleapi.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName Message
 * @Description TODO
 * @Author wangpeng
 * @Date 2022/3/5 13:38
 */
@Data
public class Student implements Serializable {
    private static final long serialVersionUID=1L;

    private Integer id;

    private String name;

    private Integer sex;

    private BigDecimal score;
}

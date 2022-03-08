package com.example.pazsapi.entity;

import lombok.Data;

import java.time.LocalDate;

/**
 * @ClassName Teacher
 * @Description TODO
 * @Author wangpeng
 * @Date 2022/3/8 22:55
 */
@Data
public class Teacher {

    private Integer id;
    private String name;
    private Integer sex;
    private Long classId;
    private LocalDate createTime;
}

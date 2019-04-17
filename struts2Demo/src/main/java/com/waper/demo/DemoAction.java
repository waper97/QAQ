package com.waper.demo;

import com.opensymphony.xwork2.ActionSupport;

import java.util.Currency;

/**
 * create by ${user} on 2019/4/17
 * *
 **/
public class DemoAction extends ActionSupport {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String  findId(){
        System.out.println("前端传过来的id是:"+ id);
        return SUCCESS;
    }


}

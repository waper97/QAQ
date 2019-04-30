package com.waper.controller;

/**
 * create by ${user} on 2019/4/30
 * *
 **/
public class BaseController {
    private int code ;

    private String msg;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static final int SUCCESS = 200;
    public static final  int FAILE = 499;

   public BaseController(Object data) {
        this.code = SUCCESS;
        this.data = data;
        this.msg = "请求成功";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

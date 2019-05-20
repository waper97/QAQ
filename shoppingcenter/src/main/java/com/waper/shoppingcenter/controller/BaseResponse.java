package com.waper.shoppingcenter.controller;

import lombok.Data;

/**
 * create by  on 2019/5/20
 * *
 **/
@Data
public class BaseResponse {

    public static final Integer SUCCESS_CODE = 200;// 成功
    public static final Integer FAILURE_CODE = 499;//失败

    public BaseResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }



    // 状态码
    private Integer code;
    // 信息
    private String message;
    // 数据
    private Object data;

    private Integer pageCount; //总数



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

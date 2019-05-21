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


    // 状态码
    private Integer code = 200;

    // 成功
    private boolean success = true;
    // 信息
    private String msg;
    // 数据
    private Object data;

    public BaseResponse(boolean success, String msg, Object data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(String msg) {
        this.success = true;
        this.msg = msg;
    }

    public BaseResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }


    public BaseResponse(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }


}

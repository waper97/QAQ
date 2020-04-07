package com.waper.shoppingcenter.controller;

import lombok.Data;

/**
 * create by  on 2019/5/20
 * *
 **/
@Data
public class BaseResponse {
    /**
     * 成功
     */
    public static final Integer SUCCESS_CODE = 200;
    /**
     * 失败
     */
    public static final Integer FAILURE_CODE = 499;

    /**
     * 成功消息
     */
    public static final String SUCCCESS_MESSAGE = "获取成功!";


    /**
     *  状态码
     */
    private Integer code = 200;


    /**
     * 成功
     */
    private boolean success = true;
    /**
     * 信息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;
    /**
     * 总条数
     */
    private long total;
    /**
     * 每页大小
     */
    private Integer pageCount;






    public BaseResponse(boolean success, String msg, Object data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(boolean success, Object data, long total, Integer pageCount) {
        this.success = success;
        this.msg = SUCCCESS_MESSAGE;
        this.data = data;
        this.total = total;
        this.pageCount = pageCount;
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

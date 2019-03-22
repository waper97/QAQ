package com.example.util;


import lombok.Data;

import java.io.Serializable;

/**
 * @Title
 * @Description:    异常处理类
 * @Author:         wangpeng
 * @CreateDate:     2019/3/21 14:56
 */
@Data
public class WebExecptionResolve implements Serializable {

        private static final int SUCCESS_CODE = 200; //成功
        private static final int FAILURE_CODE = 499; //失败
        private static final int ERROR_CODE = 2333;  //系统异常

        private boolean success;
        private String message;
        private Integer code;

    public WebExecptionResolve(boolean success, String message, Integer code) {
        this.success = success;
        this.message = message;
        this.code = code;
    }

    public WebExecptionResolve(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public WebExecptionResolve(boolean success, Integer code) {
        this.success = success;
        this.code = code;
    }
}

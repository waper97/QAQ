package com.yj.common.model;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BaseRes implements Serializable {
	public static final int SUCCESS_CODE = 200;//成功
	public static final int LOGIN_CODE = 401 ;//用户未登录，强制用户进行登录操作
	public static final int ACCSESS_TOKEN_EXPIRE_CODE = 402;//访问token过期
	public static final int FAILURE_CODE = 499;//失败

	private boolean success = true; 
	private Integer code;
	private String message;
	private Long total=null;
	private Integer pageCount=null;
	private Object data;

	public BaseRes(boolean success,int code, String msg) {
		this.success = success;
		this.code = code;
		this.message = msg;
	}
	
	public BaseRes(boolean success, Object data) {
		this.success = success;
		if(success)
			this.code = SUCCESS_CODE;
		else
			this.code = FAILURE_CODE;
		if(data!=null&&data.getClass().getName().indexOf("java.lang.String")>=0){
			this.message = (String)data;
		}
		else{
			this.message = "获取成功！";
			this.data = data;		
		}
	}
	
	public BaseRes(String msg) {
		this.success=true;
		this.code = SUCCESS_CODE;
		this.message = msg;
	}
	public BaseRes(String msg, Object data) {
		this.success=true;
		this.code = SUCCESS_CODE;
		this.message = msg;
		this.data = data;		
	}
	
	public BaseRes(int code,String msg, Object data) {
		this.success=true;
		this.code = code;
		this.message = msg;
		this.data = data;
	}

	public BaseRes(String msg,long total,int pageCount,Object data) {
		this.success=true;
		this.code = SUCCESS_CODE;
		this.message = msg;
		this.total = total;
		this.pageCount = pageCount;
		this.data = data;
	}

	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

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

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	
	public static void setResponse(String origin,HttpServletResponse response){
		response.setContentType("application/Json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragrma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		//允许拦截器跨域
		response.addHeader("Access-Control-Allow-Origin",origin);
        response.addHeader("Access-Control-Allow-Methods","POST, GET, OPTIONS, DELETE");
        response.addHeader("Access-Control-Max-Age","3600");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
	}
}

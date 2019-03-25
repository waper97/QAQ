package com.yj.hqbz.model.system;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorLog {

	private Integer id;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date errorTime;

	private String errorClass;

	private String errorMethod;

	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getErrorTime() {
		return errorTime;
	}

	public void setErrorTime(Date errorTime) {
		this.errorTime = errorTime;
	}

	public String getErrorClass() {
		return errorClass;
	}

	public void setErrorClass(String errorClass) {
		this.errorClass = errorClass;
	}

	public String getErrorMethod() {
		return errorMethod;
	}

	public void setErrorMethod(String errorMethod) {
		this.errorMethod = errorMethod;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
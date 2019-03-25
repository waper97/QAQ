package com.yj.hqbz.model.schoolOperation;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AttendanceDetail {
    private String id;

    private String indexid;

    private String userid;
    
    private String name;
    
    private String userCode;
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;
    
	private Integer sex;

    private Integer result;
    
    private Integer age;

	private String reason;
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Integer getAge() {
    	return age;
    }
    
    public void setAge(Integer age) {
    	this.age = age;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


	public String getIndexid() {
		return indexid;
	}

	public void setIndexid(String indexid) {
		this.indexid = indexid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}
    
    
}
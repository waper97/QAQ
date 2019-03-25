package com.yj.hqbz.model.schoolOperation;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AttendanceIndex {
	
    private String indexid;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date attDate;

    private String userid;
    
    private String name;  //用户姓名

    private Integer count;

    private Integer status;

    private Integer orgid;
    
    private String orgName;

    private String creator;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private String lastOpUser;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastOpDate;
    
    private String summary;//晨检结果（总结） 全部合格、全部不合格、部分不合格
    
    private Integer okPeople;//合格人数
    
    private Integer notOk;//不合格人数
    
    private Integer lack;//缺勤人数  含病假、事假、无故三种缺勤
    
    private List<AttendanceDetail> resultList;//考勤明细
    
    public List<AttendanceDetail> getResultList() {
		return resultList;
	}

	public void setResultList(List<AttendanceDetail> resultList) {
		this.resultList = resultList;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getOkPeople() {
		return okPeople;
	}

	public void setOkPeople(Integer okPeople) {
		this.okPeople = okPeople;
	}

	public Integer getNotOk() {
		return notOk;
	}

	public void setNotOk(Integer notOk) {
		this.notOk = notOk;
	}

	public Integer getLack() {
		return lack;
	}

	public void setLack(Integer lack) {
		this.lack = lack;
	}

	public String getIndexid() {
        return indexid;
    }

    public void setIndexid(String indexid) {
        this.indexid = indexid;
    }

    public Date getAttDate() {
        return attDate;
    }

    public void setAttDate(Date attDate) {
        this.attDate = attDate;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLastOpUser() {
		return lastOpUser;
	}

	public void setLastOpUser(String lastOpUser) {
		this.lastOpUser = lastOpUser;
	}

	public Date getLastOpDate() {
		return lastOpDate;
	}

	public void setLastOpDate(Date lastOpDate) {
		this.lastOpDate = lastOpDate;
	}
}
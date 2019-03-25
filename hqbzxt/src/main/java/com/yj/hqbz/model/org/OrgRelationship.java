package com.yj.hqbz.model.org;

public class OrgRelationship {
    private Integer id;

    private Integer orgnOrgid;

    private String orgnOrgName;
    
    private Integer orgnOrgStatus;
    
    private Integer targetOrgid;
    
    private String targetOrgName;
    
    private Integer targetOrgStatus;
    
    private String beginTimeStr;
    
    private String endTimeStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public Integer getOrgnOrgid() {
		return orgnOrgid;
	}

	public void setOrgnOrgid(Integer orgnOrgid) {
		this.orgnOrgid = orgnOrgid;
	}

	public String getOrgnOrgName() {
		return orgnOrgName;
	}

	public void setOrgnOrgName(String orgnOrgName) {
		this.orgnOrgName = orgnOrgName;
	}

	public Integer getTargetOrgid() {
		return targetOrgid;
	}

	public void setTargetOrgid(Integer targetOrgid) {
		this.targetOrgid = targetOrgid;
	}

	public String getTargetOrgName() {
		return targetOrgName;
	}

	public void setTargetOrgName(String targetOrgName) {
		this.targetOrgName = targetOrgName;
	}

	public Integer getOrgnOrgStatus() {
		return orgnOrgStatus;
	}

	public void setOrgnOrgStatus(Integer orgnOrgStatus) {
		this.orgnOrgStatus = orgnOrgStatus;
	}

	public Integer getTargetOrgStatus() {
		return targetOrgStatus;
	}

	public void setTargetOrgStatus(Integer targetOrgStatus) {
		this.targetOrgStatus = targetOrgStatus;
	}

	public String getBeginTimeStr() {
		return beginTimeStr;
	}

	public void setBeginTimeStr(String beginTimeStr) {
		this.beginTimeStr = beginTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	

	

   
}
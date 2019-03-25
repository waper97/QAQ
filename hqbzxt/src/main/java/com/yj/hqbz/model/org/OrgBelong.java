package com.yj.hqbz.model.org;

import java.util.Date;

public class OrgBelong {
    private Integer id;

    private String orgids;
    
    private Integer yjOrgid;

    private String yjOrgName;

    private Integer areaid;

    private Integer status;

    private Date openDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYjOrgid() {
		return yjOrgid;
	}

	public void setYjOrgid(Integer yjOrgid) {
		this.yjOrgid = yjOrgid;
	}

	public String getYjOrgName() {
		return yjOrgName;
	}

	public void setYjOrgName(String yjOrgName) {
		this.yjOrgName = yjOrgName;
	}

	public String getOrgids() {
		return orgids;
	}

	public void setOrgids(String orgids) {
		this.orgids = orgids;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}


    
}
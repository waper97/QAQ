package com.yj.hqbz.model.org;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Organization {
    private Integer orgid;

    private String name;

    private String code;

    private Integer orgType;
    
    private String orgTypeName;

    private String scopeType;
    
    private String scopeTypeName;

    private String namepy;

    private Integer orgStyle;

    private String orgNature;

    private Integer openSeller=0;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date openDate;

    private String businessState;

    private String capital;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date redDate;

    private String creditCode;

    private String nature;

    private String regauthor;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date beginTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;

    private String legalPerson;

    private String linkman;

    private String phone;

    private Integer area;
    
    private String areaName;

    private String address;
    
    private String deliverTime;

    private String scope;

    private String licensePic;

    private String licensekeyPic;

    private String tradeMark;

    private String ratePaying;

    private String remark;

    private Integer status;

    private String creator;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    private String lastOpUser;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastOpDate;

    private List<OrgRelationship> orgRelation;
    
    private List<CustomerBanner> banner;
    
    private OrgBelong belong;
    
    private String schoolName;
    
	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}

	public String getScopeType() {
		return scopeType;
	}

	public void setScopeType(String scopeType) {
		this.scopeType = scopeType;
	}

	public String getNamepy() {
		return namepy;
	}

	public void setNamepy(String namepy) {
		this.namepy = namepy;
	}

	public Integer getOrgStyle() {
		return orgStyle;
	}

	public void setOrgStyle(Integer orgStyle) {
		this.orgStyle = orgStyle;
	}

	public String getOrgNature() {
		return orgNature;
	}

	public void setOrgNature(String orgNature) {
		this.orgNature = orgNature;
	}

	public Integer getOpenSeller() {
		return openSeller;
	}

	public void setOpenSeller(Integer openSeller) {
		this.openSeller = openSeller;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public String getBusinessState() {
		return businessState;
	}

	public void setBusinessState(String businessState) {
		this.businessState = businessState;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public Date getRedDate() {
		return redDate;
	}

	public void setRedDate(Date redDate) {
		this.redDate = redDate;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getRegauthor() {
		return regauthor;
	}

	public void setRegauthor(String regauthor) {
		this.regauthor = regauthor;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getLicensePic() {
		return licensePic;
	}

	public void setLicensePic(String licensePic) {
		this.licensePic = licensePic;
	}

	public String getLicensekeyPic() {
		return licensekeyPic;
	}

	public void setLicensekeyPic(String licensekeyPic) {
		this.licensekeyPic = licensekeyPic;
	}

	public String getTradeMark() {
		return tradeMark;
	}

	public void setTradeMark(String tradeMark) {
		this.tradeMark = tradeMark;
	}

	public String getRatePaying() {
		return ratePaying;
	}

	public void setRatePaying(String ratePaying) {
		this.ratePaying = ratePaying;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}

	public String getScopeTypeName() {
		return scopeTypeName;
	}

	public void setScopeTypeName(String scopeTypeName) {
		this.scopeTypeName = scopeTypeName;
	}

	public String getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(String deliverTime) {
		this.deliverTime = deliverTime;
	}

	public List<OrgRelationship> getOrgRelation() {
		return orgRelation;
	}

	public void setOrgRelation(List<OrgRelationship> orgRelation) {
		this.orgRelation = orgRelation;
	}

	public List<CustomerBanner> getBanner() {
		return banner;
	}

	public void setBanner(List<CustomerBanner> banner) {
		this.banner = banner;
	}

	public OrgBelong getBelong() {
		return belong;
	}

	public void setBelong(OrgBelong belong) {
		this.belong = belong;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
    
	
}
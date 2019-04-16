package com.yj.hqbz.model.schoolOperation;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FoodAdditive {
	
    private String id;

    private String code;

    private String aliableid;

    private String personAliable;

    private Integer menuType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date useDate;

    private Integer orgid;

    private String orgName;
    
    private String detailid;

    private BigDecimal qty ;

    private BigDecimal percent;

    private String remark;

    private Integer status = 0;

    private String creator;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private String lastOpUser;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastOpDate;
    
    private String goodsName;
    private String aliasName;
    private String unit;
    private String goodsType;
    private String outOrderNo;
    private String relOrderNo;
    private BigDecimal outQty;
    private BigDecimal availableQty; //可用数量
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date outDate;
    private String warehouse;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date prodate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date intervaldate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date uselifedate;
    private String outPerson;
    private Integer purpose;
    

	public BigDecimal getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(BigDecimal availableQty) {
		this.availableQty = availableQty;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getOutOrderNo() {
		return outOrderNo;
	}

	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}

	public String getRelOrderNo() {
		return relOrderNo;
	}

	public void setRelOrderNo(String relOrderNo) {
		this.relOrderNo = relOrderNo;
	}

	public BigDecimal getOutQty() {
		return outQty;
	}

	public void setOutQty(BigDecimal outQty) {
		this.outQty = outQty;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public Date getProdate() {
		return prodate;
	}

	public void setProdate(Date prodate) {
		this.prodate = prodate;
	}

	public Date getIntervaldate() {
		return intervaldate;
	}

	public void setIntervaldate(Date intervaldate) {
		this.intervaldate = intervaldate;
	}

	public Date getUselifedate() {
		return uselifedate;
	}

	public void setUselifedate(Date uselifedate) {
		this.uselifedate = uselifedate;
	}

	public String getOutPerson() {
		return outPerson;
	}

	public void setOutPerson(String outPerson) {
		this.outPerson = outPerson;
	}

	public Integer getPurpose() {
		return purpose;
	}

	public void setPurpose(Integer purpose) {
		this.purpose = purpose;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}



	public String getAliableid() {
		return aliableid;
	}

	public void setAliableid(String aliableid) {
		this.aliableid = aliableid;
	}


	public String getPersonAliable() {
		return personAliable;
	}

	public void setPersonAliable(String personAliable) {
		this.personAliable = personAliable;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
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

	public String getDetailid() {
		return detailid;
	}

	public void setDetailid(String detailid) {
		this.detailid = detailid;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
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

}
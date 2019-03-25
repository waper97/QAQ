package com.yj.hqbz.model.sporadic;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SporadicIndex {
    private String id;

    private String voucherCode;

    private Integer orgid;

    private BigDecimal total;

    private String purchaserid;

    private String purchaser;
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date useDate;

    private Integer putInWarehouse;

    private Integer status;

    private String remark;

    private Integer deleteStatus;

    private String creator;
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private String lastOpUser;
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastOpDate;
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inStoreDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getPurchaserid() {
		return purchaserid;
	}

	public void setPurchaserid(String purchaserid) {
		this.purchaserid = purchaserid;
	}

	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public Integer getPutInWarehouse() {
		return putInWarehouse;
	}

	public void setPutInWarehouse(Integer putInWarehouse) {
		this.putInWarehouse = putInWarehouse;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
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

	public Date getInStoreDate() {
		return inStoreDate;
	}

	public void setInStoreDate(Date inStoreDate) {
		this.inStoreDate = inStoreDate;
	}

    
}
package com.yj.hqbz.model.goods;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrgSku {
    private Integer orgSkuid;

    private Integer orgid;
    
    private String orgName;

    private String skuid;
    
    private String skuCode;
    
    private String property;
    
    private String goodsid;
    
    private String goosCode;

    private String goodsName;
    
    private String aliasName;
    
    private Integer goodsTypeid;
    
    private String goodsTypeName;

    private String unit;
    
    private String auixUnit;

    private BigDecimal auixRate;

    private BigDecimal price;

    private BigDecimal priceBasic;

    private BigDecimal costPrice;

    private BigDecimal costPriceBasic;

    private String qty;
    
    private String qtyBasic;

    private Integer status;

    private Integer deleteStatus;

    private String creator;
    
    public String picUrl;
    
    public String thumbnailUrl;
    
    private String specInfo;

    @JsonFormat(pattern="yyyy-MM-dd HH:MM:ss",timezone = "GMT+8")
    private Date createDate;

    private String lastOpUser;

    @JsonFormat(pattern="yyyy-MM-dd HH:MM:ss",timezone = "GMT+8")
    private Date lastOpDate;

	public Integer getOrgSkuid() {
		return orgSkuid;
	}

	public void setOrgSkuid(Integer orgSkuid) {
		this.orgSkuid = orgSkuid;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public String getSkuid() {
		return skuid;
	}

	public void setSkuid(String skuid) {
		this.skuid = skuid;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getGoosCode() {
		return goosCode;
	}

	public void setGoosCode(String goosCode) {
		this.goosCode = goosCode;
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

	public Integer getGoodsTypeid() {
		return goodsTypeid;
	}

	public void setGoodsTypeid(Integer goodsTypeid) {
		this.goodsTypeid = goodsTypeid;
	}

	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getAuixUnit() {
		return auixUnit;
	}

	public void setAuixUnit(String auixUnit) {
		this.auixUnit = auixUnit;
	}

	public BigDecimal getAuixRate() {
		return auixRate;
	}

	public void setAuixRate(BigDecimal auixRate) {
		this.auixRate = auixRate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPriceBasic() {
		return priceBasic;
	}

	public void setPriceBasic(BigDecimal priceBasic) {
		this.priceBasic = priceBasic;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getCostPriceBasic() {
		return costPriceBasic;
	}

	public void setCostPriceBasic(BigDecimal costPriceBasic) {
		this.costPriceBasic = costPriceBasic;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getQtyBasic() {
		return qtyBasic;
	}

	public void setQtyBasic(String qtyBasic) {
		this.qtyBasic = qtyBasic;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getSpecInfo() {
		return specInfo;
	}

	public void setSpecInfo(String specInfo) {
		this.specInfo = specInfo;
	}

	

	public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

   
}
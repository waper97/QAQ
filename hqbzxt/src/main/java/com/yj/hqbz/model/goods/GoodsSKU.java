package com.yj.hqbz.model.goods;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GoodsSKU {
    private String skuid;

    private String skuCode;

    private String goodsid;

    private String property;

    private String spec;

    private Integer sortNo;

    private Integer status=0;

    private String creator;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private String lasteDitor;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lasteDitDate;
    
    private List<GoodsPic> goodsPic;
    
    private String picUrl;
    
    private String thumbnailUrl;
    
    private List<OrgSku> orgSkuList;

	public List<OrgSku> getOrgSkuList() {
		return orgSkuList;
	}

	public void setOrgSkuList(List<OrgSku> orgSkuList) {
		this.orgSkuList = orgSkuList;
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

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}


	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
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

	public String getLasteDitor() {
		return lasteDitor;
	}

	public void setLasteDitor(String lasteDitor) {
		this.lasteDitor = lasteDitor;
	}

	public Date getLasteDitDate() {
		return lasteDitDate;
	}

	public void setLasteDitDate(Date lasteDitDate) {
		this.lasteDitDate = lasteDitDate;
	}

	public List<GoodsPic> getGoodsPic() {
		return goodsPic;
	}

	public void setGoodsPic(List<GoodsPic> goodsPic) {
		this.goodsPic = goodsPic;
	}

    
}
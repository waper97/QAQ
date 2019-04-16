package com.yj.hqbz.model.trace;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GoodsTrace {
    private String id;

    private String orderid;

    private String orderDetailid;

    private Integer traceType;

    private String goodsid;

    private String goodsSkuid;

    private Integer orgid;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addDate;

    private String batchNo;

    private BigDecimal sortQty;

    private String supplier;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date proDate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date intervalDate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date uselifeDate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date buyDate;
    
    private String skuCode;
    
    private List<GoodsTracePic> picList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getOrderDetailid() {
		return orderDetailid;
	}

	public void setOrderDetailid(String orderDetailid) {
		this.orderDetailid = orderDetailid;
	}

	public Integer getTraceType() {
		return traceType;
	}

	public void setTraceType(Integer traceType) {
		this.traceType = traceType;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getGoodsSkuid() {
		return goodsSkuid;
	}

	public void setGoodsSkuid(String goodsSkuid) {
		this.goodsSkuid = goodsSkuid;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}


	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public BigDecimal getSortQty() {
		return sortQty;
	}

	public void setSortQty(BigDecimal sortQty) {
		this.sortQty = sortQty;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Date getProDate() {
		return proDate;
	}

	public void setProDate(Date proDate) {
		this.proDate = proDate;
	}

	public Date getIntervalDate() {
		return intervalDate;
	}

	public void setIntervalDate(Date intervalDate) {
		this.intervalDate = intervalDate;
	}

	public Date getUselifeDate() {
		return uselifeDate;
	}

	public void setUselifeDate(Date uselifeDate) {
		this.uselifeDate = uselifeDate;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

    public List<GoodsTracePic> getPicList() {
        return picList;
    }

    public void setPicList(List<GoodsTracePic> picList) {
        this.picList = picList;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
	
    
}
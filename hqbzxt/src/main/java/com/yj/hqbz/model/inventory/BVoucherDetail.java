package com.yj.hqbz.model.inventory;

import java.math.BigDecimal;
import java.util.Date;

public class BVoucherDetail {
    private String id;

    private String indexid;

    private Integer goodsid;

    private String skuid;

    private String traceid;

    private String traceNo;

    private Date proDate;

    private Date intervalDate;

    private Date uselifeDate;

    private Date inDate;

    private BigDecimal qty;

    private BigDecimal price;

    private String warehouse;

    private BigDecimal total;

    private String stockid;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndexid() {
		return indexid;
	}

	public void setIndexid(String indexid) {
		this.indexid = indexid;
	}

	public Integer getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public String getSkuid() {
		return skuid;
	}

	public void setSkuid(String skuid) {
		this.skuid = skuid;
	}

	public String getTraceid() {
		return traceid;
	}

	public void setTraceid(String traceid) {
		this.traceid = traceid;
	}

	public String getTraceNo() {
		return traceNo;
	}

	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
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


	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getStockid() {
		return stockid;
	}

	public void setStockid(String stockid) {
		this.stockid = stockid;
	}

   
}
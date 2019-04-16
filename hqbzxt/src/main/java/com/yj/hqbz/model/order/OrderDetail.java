package com.yj.hqbz.model.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderDetail {
    private String detailid;

    private String orderid;

    private String orgSkuid;

    private BigDecimal qty;

    private BigDecimal qtyBasic;

    private String unit;

    private String auixUnit;

    private BigDecimal price;

    private BigDecimal priceBasic;
    
    private BigDecimal costprice;

    private BigDecimal costpriceBasic;

    private Integer detailStatus = 0;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    private BigDecimal outQty = new BigDecimal("0");

    private BigDecimal realQty = new BigDecimal("0");

    private String specInfo;

    private Integer diffStatus = 0;

    private BigDecimal diffQty =new BigDecimal("0");

    private String diffDesc;
    
    private Integer createPurchaseOrder = 0;
    
    private Integer instoreStatus = 0;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date instoreDate;
    
    private Integer businessAttr;
    
    private Integer deleteStatus = 0;
    
    private BigDecimal diffTotal;
    
    private String goodsName;
    private String aliasName;
    private String property;
    private String picUrl;
    private String thumbnailUrl;
    private BigDecimal realTotal;
    private BigDecimal total;
    private BigDecimal outTotal;
    private BigDecimal returnQty = new BigDecimal("0");;
    private BigDecimal returnRealQty = new BigDecimal("0");;
    
    private Integer traceStatus;
    private Integer recordCount;
    
    private String sortNo;
    private List<OrderDetail> goodsTraceList;
    
    private String goodsid;

	public Integer getCreatePurchaseOrder() {
		return createPurchaseOrder;
	}

	public void setCreatePurchaseOrder(Integer createPurchaseOrder) {
		this.createPurchaseOrder = createPurchaseOrder;
	}

	public String getDetailid() {
		return detailid;
	}

	public void setDetailid(String detailid) {
		this.detailid = detailid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getOrgSkuid() {
		return orgSkuid;
	}

	public void setOrgSkuid(String orgSkuid) {
		this.orgSkuid = orgSkuid;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getQtyBasic() {
		return qtyBasic;
	}

	public void setQtyBasic(BigDecimal qtyBasic) {
		this.qtyBasic = qtyBasic;
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

	public BigDecimal getCostpriceBasic() {
		return costpriceBasic;
	}

	public void setCostpriceBasic(BigDecimal costpriceBasic) {
		this.costpriceBasic = costpriceBasic;
	}

	public Integer getDetailStatus() {
		return detailStatus;
	}

	public void setDetailStatus(Integer detailStatus) {
		this.detailStatus = detailStatus;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public BigDecimal getOutQty() {
		return outQty;
	}

	public void setOutQty(BigDecimal outQty) {
		this.outQty = outQty;
	}

	public BigDecimal getRealQty() {
		return realQty;
	}

	public void setRealQty(BigDecimal realQty) {
		this.realQty = realQty;
	}

	public String getSpecInfo() {
		return specInfo;
	}

	public void setSpecInfo(String specInfo) {
		this.specInfo = specInfo;
	}

	public Integer getDiffStatus() {
		return diffStatus;
	}

	public void setDiffStatus(Integer diffStatus) {
		this.diffStatus = diffStatus;
	}

	public BigDecimal getDiffQty() {
		return diffQty;
	}

	public void setDiffQty(BigDecimal diffQty) {
		this.diffQty = diffQty;
	}

	public String getDiffDesc() {
		return diffDesc;
	}

	public void setDiffDesc(String diffDesc) {
		this.diffDesc = diffDesc;
	}

	public Integer getInstoreStatus() {
		return instoreStatus;
	}

	public void setInstoreStatus(Integer instoreStatus) {
		this.instoreStatus = instoreStatus;
	}

	public Date getInstoreDate() {
		return instoreDate;
	}

	public void setInstoreDate(Date instoreDate) {
		this.instoreDate = instoreDate;
	}

    public Integer getBusinessAttr() {
        return businessAttr;
    }

    public void setBusinessAttr(Integer businessAttr) {
        this.businessAttr = businessAttr;
    }

	public BigDecimal getCostprice() {
		return costprice;
	}

	public void setCostprice(BigDecimal costprice) {
		this.costprice = costprice;
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

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
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

    public BigDecimal getRealTotal() {
        return realTotal;
    }

    public void setRealTotal(BigDecimal realTotal) {
        this.realTotal = realTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getOutTotal() {
        return outTotal;
    }

    public void setOutTotal(BigDecimal outTotal) {
        this.outTotal = outTotal;
    }

    public BigDecimal getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(BigDecimal returnQty) {
        this.returnQty = returnQty;
    }

    public BigDecimal getReturnRealQty() {
        return returnRealQty;
    }

    public void setReturnRealQty(BigDecimal returnRealQty) {
        this.returnRealQty = returnRealQty;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public BigDecimal getDiffTotal() {
        return diffTotal;
    }

    public void setDiffTotal(BigDecimal diffTotal) {
        this.diffTotal = diffTotal;
    }

    public Integer getTraceStatus() {
        return traceStatus;
    }

    public void setTraceStatus(Integer traceStatus) {
        this.traceStatus = traceStatus;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public String getSortNo() {
        return sortNo;
    }

    public void setSortNo(String sortNo) {
        this.sortNo = sortNo;
    }

    public List<OrderDetail> getGoodsTraceList() {
        return goodsTraceList;
    }

    public void setGoodsTraceList(List<OrderDetail> goodsTraceList) {
        this.goodsTraceList = goodsTraceList;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }
    

    
}
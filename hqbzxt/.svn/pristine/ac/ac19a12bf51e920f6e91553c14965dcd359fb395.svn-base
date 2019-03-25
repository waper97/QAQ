package com.yj.hqbz.model.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yj.hqbz.util.DateUtil;

public class ReturnOrder {
    private String orderid; //退货订单ID

    private String orderNo; //退货订单号

    private String relOrderid;   //关联订单ID

    private String relOrderDetailid; //关联订单明细
    
    private Integer orderType;	//订单类型

    private Integer status;	//订单状态

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;  //申请日期

    private String orgSkuid; //机构商品skuID
    private String specInfo;	//销售规格
    
    private BigDecimal salePrice;
    private BigDecimal costPrice;
    private BigDecimal buyOrderQty;
    private BigDecimal buyOrderTotal;
    private BigDecimal outQty;
    private BigDecimal outTotal;
    private BigDecimal buyOrderRealQty;	//购货订单实际收货数量
    private BigDecimal buyOrderRealTotal;	//购货订单实际收货金额
    
    
    private String goodsName;
    private String aliasName;
    private String unit;
    private String auixUnit;	//销售单位
    private String property;	//商品SKU规格
    private String picUrl;
    private String thumbnailUrl;
    

    private BigDecimal count; //退货数量	

    private String reason;  //退货原因

    private BigDecimal costPriceBasic;	//成本单价

    private BigDecimal returnTotal ; //申请退货总金额
 
    private BigDecimal receiveQty = new BigDecimal(0); //实际退货数量

    private String userid;  //申请退货用户ID
    private String userName;

    private Integer buyerOrgId; // 退货客户ID
    private String buyerOrgName; //退货机构名称
    
    private Integer orgid;	//商家ID
    private String orgName; //商家机构名称

    private Integer deleteStatus;

    private String[] orderStatus;  //订单状态
    
    private List<OrderLog> log;	//日志信息
    private List<OrderLogistics> logistics;	//物流信息	

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getRelOrderid() {
		return relOrderid;
	}

	public void setRelOrderid(String relOrderid) {
		this.relOrderid = relOrderid;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}


	public String getRelOrderDetailid() {
		return relOrderDetailid;
	}

	public void setRelOrderDetailid(String relOrderDetailid) {
		this.relOrderDetailid = relOrderDetailid;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOrgSkuid() {
		return orgSkuid;
	}

	public void setOrgSkuid(String orgSkuid) {
		this.orgSkuid = orgSkuid;
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

	public BigDecimal getCount() {
		return count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getReturnTotal() {
		return returnTotal;
	}

	public void setReturnTotal(BigDecimal returnTotal) {
		this.returnTotal = returnTotal;
	}

	public BigDecimal getReceiveQty() {
		return receiveQty;
	}

	public void setReceiveQty(BigDecimal receiveQty) {
		this.receiveQty = receiveQty;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getBuyerOrgId() {
		return buyerOrgId;
	}

	public void setBuyerOrgId(Integer buyerOrgId) {
		this.buyerOrgId = buyerOrgId;
	}

	public String getBuyerOrgName() {
		return buyerOrgName;
	}

	public void setBuyerOrgName(String buyerOrgName) {
		this.buyerOrgName = buyerOrgName;
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

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String[] getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String[] orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<OrderLog> getLog() {
		return log;
	}

	public void setLog(List<OrderLog> log) {
		this.log = log;
	}

	public List<OrderLogistics> getLogistics() {
		return logistics;
	}

	public void setLogistics(List<OrderLogistics> logistics) {
		this.logistics = logistics;
	}

	public String getSpecInfo() {
		return specInfo;
	}

	public void setSpecInfo(String specInfo) {
		this.specInfo = specInfo;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getBuyOrderQty() {
		return buyOrderQty;
	}

	public void setBuyOrderQty(BigDecimal buyOrderQty) {
		this.buyOrderQty = buyOrderQty;
	}

	public BigDecimal getBuyOrderTotal() {
		return buyOrderTotal;
	}

	public void setBuyOrderTotal(BigDecimal buyOrderTotal) {
		this.buyOrderTotal = buyOrderTotal;
	}

	public BigDecimal getOutQty() {
		return outQty;
	}

	public void setOutQty(BigDecimal outQty) {
		this.outQty = outQty;
	}

	public BigDecimal getOutTotal() {
		return outTotal;
	}

	public void setOutTotal(BigDecimal outTotal) {
		this.outTotal = outTotal;
	}

	public BigDecimal getBuyOrderRealQty() {
		return buyOrderRealQty;
	}

	public void setBuyOrderRealQty(BigDecimal buyOrderRealQty) {
		this.buyOrderRealQty = buyOrderRealQty;
	}

	public BigDecimal getBuyOrderRealTotal() {
		return buyOrderRealTotal;
	}

	public void setBuyOrderRealTotal(BigDecimal buyOrderRealTotal) {
		this.buyOrderRealTotal = buyOrderRealTotal;
	}

	public String getAuixUnit() {
		return auixUnit;
	}

	public void setAuixUnit(String auixUnit) {
		this.auixUnit = auixUnit;
	}

	public BigDecimal getCostPriceBasic() {
		return costPriceBasic;
	}

	public void setCostPriceBasic(BigDecimal costPriceBasic) {
		this.costPriceBasic = costPriceBasic;
	}

}
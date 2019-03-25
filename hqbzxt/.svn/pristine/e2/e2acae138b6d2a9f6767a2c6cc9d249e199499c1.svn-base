package com.yj.hqbz.model.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderForm {
    private String id;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    private Integer deleteStatus = 0;

    private String msg;

    private String orderNo;

    private String sortingNo;

    private Integer orderStatus;

    private Integer orderType;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expectRecvBeginTime;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expectRecvEndTime;

    private Integer shipPrice;

    private BigDecimal total;

    private BigDecimal outTotal;

    private BigDecimal costTotal;

    private BigDecimal receiveTotal;

    private Integer storeid;

    private String storeName;
    
    private String userid;
    
    private String userName;

    private String parentid;

    private Integer orgid;

    private String orgName;
    
    private String receiver;

    private String receiverMobile;

    private String receiverAddress;

    private Integer payStatus;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payDate;
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deliveryDate;
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receiveDate;
    
    private List<OrderDetail> orderDetails;
    
    private List<OrderLogistics> logistics;
    
    private List<OrderLog> log;
    
    private BigDecimal detailTotal;
    
    private BigDecimal returnTotal;
    
    private Integer recordCount;
    
    private Integer traceStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getSortingNo() {
		return sortingNo;
	}

	public void setSortingNo(String sortingNo) {
		this.sortingNo = sortingNo;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Date getExpectRecvBeginTime() {
		return expectRecvBeginTime;
	}

	public void setExpectRecvBeginTime(Date expectRecvBeginTime) {
		this.expectRecvBeginTime = expectRecvBeginTime;
	}

	public Date getExpectRecvEndTime() {
		return expectRecvEndTime;
	}

	public void setExpectRecvEndTime(Date expectRecvEndTime) {
		this.expectRecvEndTime = expectRecvEndTime;
	}

	public Integer getShipPrice() {
		return shipPrice;
	}

	public void setShipPrice(Integer shipPrice) {
		this.shipPrice = shipPrice;
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

	public BigDecimal getCostTotal() {
		return costTotal;
	}

	public void setCostTotal(BigDecimal costTotal) {
		this.costTotal = costTotal;
	}

	public BigDecimal getReceiveTotal() {
		return receiveTotal;
	}

	public void setReceiveTotal(BigDecimal receiveTotal) {
		this.receiveTotal = receiveTotal;
	}

	public Integer getStoreid() {

		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

   

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<OrderLogistics> getLogistics() {
        return logistics;
    }

    public void setLogistics(List<OrderLogistics> logistics) {
        this.logistics = logistics;
    }

    public List<OrderLog> getLog() {
        return log;
    }

    public void setLog(List<OrderLog> log) {
        this.log = log;
    }

    public BigDecimal getDetailTotal() {
        return detailTotal;
    }

    public void setDetailTotal(BigDecimal detailTotal) {
        this.detailTotal = detailTotal;
    }

    public BigDecimal getReturnTotal() {
        return returnTotal;
    }

    public void setReturnTotal(BigDecimal returnTotal) {
        this.returnTotal = returnTotal;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getTraceStatus() {
        return traceStatus;
    }

    public void setTraceStatus(Integer traceStatus) {
        this.traceStatus = traceStatus;
    }
    
    

}
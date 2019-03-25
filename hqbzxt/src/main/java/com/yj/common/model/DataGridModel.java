package com.yj.common.model;
/**
 * @Title: DataGridModel.java
 * @Package com.yjkj.common.model
 * @Description: TODO
 * @author xx
 * @date 2017-3-13
 */
public class DataGridModel implements java.io.Serializable {

	private static final long serialVersionUID = 7232798260610351343L;
	private int page = 1; // 当前页,名字必须为page
	private int rows = 12; // 每页大小,名字必须为rows
	private String orderBy; // 排序字段
	private int orderType=0; // 排序规则
	
	
	
	public int getPage() {
		if(page<1) {
			return 1;
		}
		return page;
	}
	public int getRows() {
		return rows;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}	
	

}

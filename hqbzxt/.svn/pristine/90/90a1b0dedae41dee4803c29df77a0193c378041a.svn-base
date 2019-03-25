package com.yj.hqbz.services.order;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yj.common.model.BaseRes;
import com.yj.hqbz.model.order.OrderLogistics;
import com.yj.hqbz.model.order.ReturnOrder;
import com.yj.hqbz.model.user.UserInfo;

/**  
 * @Title: ReturnOrderService.java
 * @Package com.yj.hqbz.services.order
 * @Description: TODO
 * @author xx
 * @date 2019-3-8
 */
public interface ReturnOrderService {
	
	/**
	 * 提交退货申请
	 * @param order
	 */
	BaseRes submitReturnApply(ReturnOrder order,UserInfo user);
	
	/**
	 * 取消退货申请
	 * @param order
	 */
	void cancelReturnApply(ReturnOrder order,UserInfo user);
	
	/**
	 * 同意或者拒绝退货申请
	 * @param order
	 * @param opinion
	 */
	void confirmOrRejectReturnApply(ReturnOrder order,boolean agree,String opinion,UserInfo user);
	
	
	
	/**
	 * 提交上门取货物流信息
	 * @param order
	 * @param logistics
	 */
	void submitPickUpOrderLogisticsInfo(ReturnOrder order,OrderLogistics logistics,UserInfo user);
	
	/**
	 * 上门取货
	 * @param order
	 */
	void pickupReturnOrder(ReturnOrder order,UserInfo user);
	
	/**
	 * 确认退货
	 * @param order
	 */
	void receiveReturnGoods(ReturnOrder order,UserInfo user);
	
	/**
	 * 根据退货订单ID获取退货订单详情
	 * @param orderid
	 * @return
	 */
	ReturnOrder getReturnOrderDetailInfo(String orderid);
	
	/**
	 * 根据退货订单ID获取退货基本信息
	 * @param orderid
	 * @return
	 */
	ReturnOrder getReturnOrderSimpleInfo(String orderid);
	
	/**
	 * 获取退货订单列表(服务于卖家)
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<ReturnOrder> getOrderListForSeller(Map<String,Object> param,int page,int rows);
	
	/**
	 * 获取退货订单列表(买家 汇总)
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<ReturnOrder> getOrderListForBuyerWithSummary(Map<String,Object> param,int page,int rows);
	
	/**
	 * 根据购货订单取得与该订单相关的退货订单	
	 * @param buyOrderid
	 * @return
	 */
	List<ReturnOrder> getReturnOrderListByBuyOrderid(String buyOrderid,String orgSkuid);
}

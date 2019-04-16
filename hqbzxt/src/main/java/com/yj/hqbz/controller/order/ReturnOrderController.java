package com.yj.hqbz.controller.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.hqbz.model.order.OrderLogistics;
import com.yj.hqbz.model.order.ReturnOrder;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.order.ReturnOrderService;
import com.yj.hqbz.util.DateUtil;
import com.yj.hqbz.util.StaticUtils;
import com.yj.hqbz.util.StringUtil;

/**  
 * @Title: ReturnOrderController.java
 * @Package com.yj.hqbz.controller.order
 * @Description: TODO
 * @author xx
 * @date 2019-3-8
 */
@RestController
public class ReturnOrderController extends BaseController{

	@Resource
	ReturnOrderService returnOrderService;
	
	//=========================买家=================================
	
	/**
	 * 获取有退货的采购订单
	 * @param orderStatus
	 * @param grid
	 * @return
	 */
	@GetMapping("/returnOrder/buyer/getSummaryList")
	public Object getOrderSummaryListForBuyer(Integer orderStatus,DataGridModel grid){
		Map<String,Object> param = new HashMap<String,Object>();
		if(orderStatus!=null)
			param.put("orderStatus", orderStatus);
		param.put("userid", getTokenUserid());
		PageInfo<ReturnOrder> page = returnOrderService.getOrderListForBuyerWithSummary(param, grid.getPage(), grid.getRows());
		return new BaseRes("ok", page.getTotal(),page.getPages(),page.getList());
	}
	
	
	@GetMapping("/returnOrder/buyer/getListByBuyOrder")
	public Object getListByBuyOrder(String orderid,String orgSkuid){
		if(StringUtil.isBlank(orderid)){
			return fail("参数非法");
		}
		else{
			List<ReturnOrder> list = returnOrderService.getReturnOrderListByBuyOrderid(orderid,orgSkuid);
			return success(list);
		}
	}
	
	/**
	 * 提交退货申请
	 * @return
	 */
	@PostMapping("/returnOrder/buyer/apply")
	public Object submitReturnApply(String orderDetailid,String reason,BigDecimal qty){
		UserInfo user = getTokenUser();
		if(user.getUserRole().intValue()!=2){  //非买家用户
			return fail("操作非法");
		}
		
		if(StringUtil.isBlank(orderDetailid) || StringUtil.isBlank(reason) || qty.compareTo(new BigDecimal(0))<=0){
			return fail("参数非法");
		}
		else{
			ReturnOrder order = new ReturnOrder();
			order.setRelOrderDetailid(orderDetailid);
			order.setCount(qty);
			order.setReason(reason);
			order.setCreateDate(new Date());
			order.setUserid(user.getUserid());
			order.setDeleteStatus(0);
			order.setBuyerOrgId(user.getOrgId());
			saveJournalLog("提交退货申请", "退货单编号:"+order.getOrderid());
			return returnOrderService.submitReturnApply(order, user);
		}		
	}
	
	
	@PostMapping("/returnOrder/buyer/cancel")
	public Object cancelOrder(String orderid){
		if(StringUtil.isBlank(orderid)){
			return fail("参数非法");
		}
		else{
			ReturnOrder order = returnOrderService.getReturnOrderSimpleInfo(orderid);
			if(order.getStatus() == StaticUtils.RETURN_ORDER_CANCEL 
					|| order.getStatus() == StaticUtils.RETURN_ORDER_FINISHED
					|| order.getStatus() == StaticUtils.RETURN_ORDER_REJECT){
			//if(order.getStatus() != StaticUtils.RETURN_ORDER_APPLY){
				return fail("该订单不允许取消");
			}
			else{
				UserInfo user = getTokenUser();
				if(!order.getUserid().equals(user.getUserid())){
					return fail("该订单只有申请人可以取消");
				}
				else{
					returnOrderService.cancelReturnApply(order, user);
					saveJournalLog("取消退货申请", "退货单编号:"+order.getOrderid());
					return success("申请已取消");
				}
			}
		}
	}
	
	/**
	 * 买家查看退货订单详情
	 * @param orderid
	 * @return
	 */
	@GetMapping("/returnOrder/buyer/getOrderInfo")
	public Object getOrderDetailInfo(String orderid){
		if(StringUtil.isBlank(orderid)){
			return fail("参数非法");
		}
		else{
			ReturnOrder order = returnOrderService.getReturnOrderDetailInfo(orderid);
			UserInfo user = getTokenUser();
			if(!order.getUserid().equals(user.getUserid())){
				return fail("参数非法");
			}
			else{
				return success(order);
			}
		}
	}
	//=========================卖家=====================================

	/**
	 * 卖家或运营管理员查看退货订单
	 * @param orderid
	 * @param status
	 * @param reason
	 * @return
	 */
	@GetMapping("/returnOrder/seller/getList")
	public Object getReturnOrderListForSeller(@RequestParam Map<String,Object> param,DataGridModel grid){
		UserInfo user = getTokenUser();
		if(user.getUserRole().intValue()==1){  //商家用户
			param.put("orgid", user.getOrgId());
			param.remove("sellerOrgName");
		}
		if(user.getUserRole().intValue()>1){
			return fail("操作非法");
		}
		
		Date startDate=null;
		Date endDate=null;
		
		if(param.get("beginTime")!=null&&param.get("beginTime").toString().trim()!=""){
			startDate = DateUtil.getDateByStr(param.get("beginTime")+" 00:00:00", "yyyy-MM-dd hh:mm:ss");
		}
		if(param.get("endTime")!=null&&param.get("endTime").toString().trim()!=""){
			endDate = DateUtil.getDateByStr(param.get("endTime")+" 23:59:59", "yyyy-MM-dd hh:mm:ss");
		}
		
		param.put("beginTime", startDate);
		param.put("endTime", endDate);
		
		PageInfo<ReturnOrder> page = returnOrderService.getOrderListForSeller(param, grid.getPage(), grid.getRows());
		return new BaseRes("Ok",page.getTotal(),page.getPages(),page.getList());
	}
	
	/**
	 * 同意或拒绝退货
	 * @param orderid
	 * @param status
	 * @param reason
	 * @return
	 */
	@PostMapping("/returnOrder/seller/confirmApply")
	public Object confirmOrRejectOrder(String orderid,Integer status,String reason){
		UserInfo user = getTokenUser();
		if(user.getUserRole()>1){  //非卖家用户或管理用户
			return fail("操作非法");
		}
		if(StringUtil.isBlank(orderid) || status == null || status>2 || status<1){
			return fail("参数非法");
		}
		else{
			if(status == 2 && StringUtil.isBlank(reason)){
				return fail("请输入拒绝原因");
			}
			else{
				ReturnOrder order = returnOrderService.getReturnOrderSimpleInfo(orderid);
				if(order == null){
					return fail("参数非法");
				}
				else{
					if(order.getStatus().intValue() == StaticUtils.RETURN_ORDER_APPLY){						
						returnOrderService.confirmOrRejectReturnApply(order, status.intValue()==1?true:false, reason, user);
						saveJournalLog((status.intValue()==1?"同意":"拒绝")+"退货申请", "退货单编号:"+order.getOrderid());
						return success("操作成功");
					}
					else{
						return fail("该订单已确认，不允许重复确认");
					}
				}
			}
		}		
	}
	
	/**
	 * 提交上门取货信息
	 * @param orderid
	 * @param distributorid
	 * @param mobile
	 * @param pickTime
	 * @return
	 */
	@PostMapping("/returnOrder/seller/submitPickUpInfo")
	public Object submitPickUpInfo(OrderLogistics logis){
		UserInfo user = getTokenUser();
		if(user.getUserRole()>1){  //非卖家用户或管理用户
			return fail("操作非法");
		}
		if(StringUtil.isBlank(logis.getOrderid()) || StringUtil.isBlank(logis.getDistributorid()) || StringUtil.isBlank(logis.getDistributor()) 
				|| StringUtil.isBlank(logis.getMobile()) || StringUtil.isBlank(logis.getDistributeDate())){
			return fail("参数非法");
		}
		else{
			ReturnOrder order = returnOrderService.getReturnOrderSimpleInfo(logis.getOrderid());
			if(order == null){
				return fail("参数非法");
			}
			else{
				if(order.getStatus()==StaticUtils.RETURN_ORDER_CONFIRMED){
					returnOrderService.submitPickUpOrderLogisticsInfo(order, logis, user);
					saveJournalLog("提交退货申请上门取货信息", "退货单编号:"+order.getOrderid());
					return success("操作成功");
				}
				else{
					return fail("该订单已确认，不允许重复确认");
				}
			}
		}	
	}
	
	/**
	 * 卖家确认退货（配送员已取货后）
	 * @param logis
	 * @return
	 */
	@PostMapping("/returnOrder/seller/confirmReturn")
	public Object confirmReturn(String orderid,BigDecimal qty){
		UserInfo user = getTokenUser();
		if(user.getUserRole()>1){  //非卖家用户或管理用户
			return fail("操作非法");
		}
		if(StringUtil.isBlank(orderid) || qty.compareTo(new BigDecimal(0))<=0){
			return fail("参数非法");
		}
		else{
			ReturnOrder order = returnOrderService.getReturnOrderSimpleInfo(orderid);
			if(order == null){
				return fail("参数非法");
			}
			else{
				if(order.getStatus() == StaticUtils.RETURN_ORDER_PROCESSING 
						|| order.getStatus() == StaticUtils.RETURN_ORDER_PICKUP){
					order.setReceiveQty(qty);
					returnOrderService.receiveReturnGoods(order, user);
					
					saveJournalLog("商家确认退货", "退货单编号:"+order.getOrderid());
					return success("操作成功");
				}
				else{
					return fail("该订单已确认，不允许重复确认");
				}
			}
		}	
	}
	
	/**
	 * 卖家获取订单详情
	 * @param orderid
	 * @param qty
	 * @return
	 */
	@GetMapping("/returnOrder/seller/getOrderInfo")
	public Object getReturnOrderDetailInfo(String orderid){
		UserInfo user = getTokenUser();
		if(user.getUserRole()>1){  //非卖家用户或管理用户
			return fail("操作非法");
		}
		if(StringUtil.isBlank(orderid)){
			return fail("参数非法");
		}
		else{
			ReturnOrder order = returnOrderService.getReturnOrderDetailInfo(orderid);
			if(order == null){
				return fail("参数非法");
			}
			else{
				return success(order);
			}
		}	
	}
	
	//======================================配送员===========================
	
	/**
	 * 取得当前用户待收货的退货单列表
	 * @param grid
	 * @return
	 */
	@GetMapping("/returnOrder/distributor/getPickupList")
	public Object getDistributorPickUpList(DataGridModel grid){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orderStatus", StaticUtils.RETURN_ORDER_PICKUP);
		param.put("distributorid", getTokenUserid());
		PageInfo<ReturnOrder> page = returnOrderService.getOrderListForSeller(param, grid.getPage(), grid.getRows());
		return new BaseRes("Ok",page.getTotal(),page.getPages(),page.getList());
	}
	
	/**
	 * 配送员确认收到退货物品
	 * @param orderid
	 * @return
	 */
	@PostMapping("/returnOrder/distributor/pickUp")
	public Object pickUpGoods(String orderid){
		UserInfo user = getTokenUser();
		if(user.getUserRole()!=1){  //非卖家用户或管理用户
			return fail("操作非法");
		}
		else{
			ReturnOrder order = returnOrderService.getReturnOrderSimpleInfo(orderid);
			if(order.getStatus() != StaticUtils.RETURN_ORDER_PICKUP){
				return fail("取货失败");
			}
			else{
				returnOrderService.pickupReturnOrder(order, user);
				
				saveJournalLog("配送员确认取货", "退货单编号:"+order.getOrderid());
				return success("取货完成");
			}
		}
	}
	
	
}
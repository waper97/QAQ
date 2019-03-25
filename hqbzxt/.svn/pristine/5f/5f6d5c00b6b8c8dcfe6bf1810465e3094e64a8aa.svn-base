package com.yj.hqbz.controller.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.hqbz.model.order.PurchaseIndex;
import com.yj.hqbz.model.order.PurchaseTask;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.order.PurchaseService;
import com.yj.hqbz.util.DateUtil;
import com.yj.hqbz.util.StringUtil;

/**  
 * @Title: 采购任务和采购单管理
 * @Package com.yj.hqbz.controller.order
 * @Description: TODO
 * @author xx
 * @date 2019-3-9
 */
@RestController
public class BuyTaskController extends BaseController{

	@Resource
	PurchaseService service;
	
	/**
	 * 获取采购任务列表
	 * @param param
	 * @param grid
	 * @return
	 */
	@GetMapping("/buyingTask/seller/getTaskList")
	public Object getPurchaseTaskList(@RequestParam Map<String,Object> param,DataGridModel grid){
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
		
		PageInfo<PurchaseTask> page = service.getPurchaseTask(param, grid.getPage(), grid.getRows());
		return new BaseRes("Ok",page.getTotal(),page.getPages(),page.getList());
	}
	
	/**
	 * 采购任务生成采购单
	 * @param params
	 * @return
	 */
	@PostMapping("/buyingTask/seller/createPurchaseOrder")
	public Object createPurchaseOrder(@RequestBody PurchaseIndex index) {
		if(index == null || index.getTask() == null){
			return fail("参数非法");
		}
		else{
			service.createPurchaseBill(index.getTask(),getTokenUser());
			return success("创建成功");
		}
	}
	
	
	//======================================== 采购单=================================
	
	@RequestMapping("/buyingTask/seller/getPurchaseOrderList")
	public Object getPurchaseOrderList(@RequestParam Map<String,Object> param,DataGridModel grid){
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
		
		PageInfo<PurchaseIndex> page = service.getPurchaseList(param, grid.getPage(), grid.getRows());
		return new BaseRes("Ok",page.getTotal(),page.getPages(),page.getList());
	}
	
	/**
	 * 查看详情
	 * @param orderid
	 * @return
	 */
	@RequestMapping("/buyingTask/seller/getPurchaseOrderInfo")
	public Object getPurchaseDetailInfo(String orderid){
		if(StringUtil.isBlank(orderid)){
			return fail("参数非法");					
		}
		else{
			PurchaseIndex index = service.getPurchaseBillDetailInfo(orderid);
			if(index == null){
				return fail("参数非法");	
			}
			else
				return success(index);
		}
	}
	
	@RequestMapping("/buyingTask/seller/deleteOrderItem")
	public Object deletePurchaseItemInfo(String orderid,String detailid){
		UserInfo user = getTokenUser();
		if(user.getUserRole().intValue()>1){
			return fail("操作非法");
		}
		
		if(StringUtil.isBlank(orderid) || StringUtil.isBlank(detailid)){
			return fail("参数非法");					
		}
		else{			
			PurchaseIndex index = service.getPurchaseBillDetailInfo(orderid);
			if(index == null){
				return fail("参数非法");	
			}
			else{
				service.deletePurchaseItem(orderid,detailid);
				return success(index);
			}
		}
	}
	
	@RequestMapping("/buyingTask/seller/submitPurchaseOrder")
	public Object submitPurchaseOrder(@RequestBody PurchaseIndex index){
		UserInfo user = getTokenUser();
		if(user.getUserRole().intValue()>1){
			return fail("操作非法");
		}
		
		if(index == null){
			return fail("参数非法");
		}
		else{
			service.updateAndSubmitPurchaseBill(index,user);
			return success("提交成功");
		}
	}
	
}

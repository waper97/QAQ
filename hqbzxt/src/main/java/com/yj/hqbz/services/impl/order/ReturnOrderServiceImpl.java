package com.yj.hqbz.services.impl.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.common.model.BaseRes;
import com.yj.hqbz.mapper.order.OrderDetailMapper;
import com.yj.hqbz.mapper.order.OrderFormMapper;
import com.yj.hqbz.mapper.order.OrderLogMapper;
import com.yj.hqbz.mapper.order.OrderLogisticsMapper;
import com.yj.hqbz.mapper.order.ReturnOrderMapper;
import com.yj.hqbz.model.order.OrderDetail;
import com.yj.hqbz.model.order.OrderForm;
import com.yj.hqbz.model.order.OrderLog;
import com.yj.hqbz.model.order.OrderLogistics;
import com.yj.hqbz.model.order.ReturnOrder;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.order.ReturnOrderService;
import com.yj.hqbz.util.DateUtil;
import com.yj.hqbz.util.StaticUtils;
import com.yj.hqbz.util.StringUtil;

/**  
 * @Title: ReturnOrderServiceImpl.java
 * @Package com.yj.hqbz.services.impl.order
 * @Description: TODO
 * @author xx
 * @date 2019-3-8
 */
@Service
public class ReturnOrderServiceImpl implements ReturnOrderService {
	
	@Resource
	ReturnOrderMapper returnOrderMapper;
	@Resource 
	OrderLogisticsMapper logisMapper;
	@Resource
	OrderFormMapper buyOrderMapper;
	@Resource
	OrderLogMapper logMapper;
	@Resource
	OrderDetailMapper buyOrderDetailMapper;
	@Resource
	OrderFormMapper buyOrderFormMapper;
	
	/**
	 * 提交退货申请
	 */
	@Transactional
	public BaseRes submitReturnApply(ReturnOrder order,UserInfo user) {		
		OrderDetail buyDetail = buyOrderDetailMapper.selectByPrimaryKey(order.getRelOrderDetailid());
		if(buyDetail == null){
			return new BaseRes(false,"参数非法!");
		}
		else{
			OrderForm form = buyOrderFormMapper.selectByPrimaryKey(buyDetail.getOrderid());
			if(form == null){
				return new BaseRes(false,"参数非法");
			}
			else{
				if(form.getOrderStatus()==60){
					if(form.getOrgid().intValue()!=order.getBuyerOrgId().intValue()){
						return new BaseRes(false,"参数非法！");
					}
					else{
						order.setOrderid(StringUtil.getUUID());
						order.setOrgid(form.getStoreid());
						order.setOrderType(form.getOrderType());
						order.setRelOrderid(buyDetail.getOrderid());
						order.setCostPriceBasic(buyDetail.getPriceBasic());
						order.setOrgSkuid(buyDetail.getOrgSkuid());
						order.setReturnTotal(order.getCostPriceBasic().multiply(order.getCount()).setScale(2,BigDecimal.ROUND_UP));
						//获取可退货的数量
						BigDecimal returnQty = returnOrderMapper.selectReturnQtyCountByBuyOrderDetailId(order.getRelOrderDetailid());
						if(order.getCount().compareTo(returnQty)>0){
							return new BaseRes(false,"退货总数不能大于实收数!");
						}
						else{
							order.setOrderNo("T"+DateUtil.getStrByDate(new Date(),"yyyyMMddHHmmss")+StringUtil.generateRandomStr(4));
							order.setOrderid(StringUtil.getUUID());
							order.setStatus(StaticUtils.RETURN_ORDER_APPLY);
							returnOrderMapper.insertReturnOrder(order);
							saveOrderLog(user,order.getOrderid(),"发起退货申请");
							buyDetail.setDetailStatus(1);
							buyOrderDetailMapper.updateOrderDetail(buyDetail);
							
							return new BaseRes(true,"提交申请成功!");
						}
					}
				}
				else{
					return new BaseRes(false,"对不起，该订单未收货，不允许进行退货！");
				}
			}
		}
	}
	
	
	private void saveOrderLog(UserInfo user,String orderid,String content){
		OrderLog log = new OrderLog();
		log.setLogDate(new Date());
		log.setOperator(user.getTrueName());
		log.setOperatorid(user.getUserid());
		log.setOrderid(orderid);
		log.setContent(content);	
		logMapper.insert(log);
	}

	@Transactional
	public void cancelReturnApply(ReturnOrder order,UserInfo user) {
		order.setStatus(StaticUtils.RETURN_ORDER_CANCEL);
		returnOrderMapper.updateOrderStatus(order);
		saveOrderLog(user,order.getOrderid(),"取消退货申请");
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("buyOrderid", order.getRelOrderid());
		param.put("orgSkuid", order.getOrgSkuid());
		
		
		//判断是否有退货订单,如果全部已取消,则修改订单明细退货状态为没有退货
		if(returnOrderMapper.getReturnOrderStatusByBuyOrderid(param).length==0){  //修改为没有退货
			OrderDetail detail = new OrderDetail();
			detail.setDetailid(order.getRelOrderDetailid());
			detail.setDetailStatus(0);
			buyOrderDetailMapper.updateOrderDetail(detail);
		}
	}
	
	@Transactional
	public void confirmOrRejectReturnApply(ReturnOrder order, boolean agree,String opinion,UserInfo user) {
		if(agree){
			order.setStatus(StaticUtils.RETURN_ORDER_CONFIRMED);
			saveOrderLog(user, order.getOrderid(), "同意退货");
		}
		else{
			order.setStatus(StaticUtils.RETURN_ORDER_REJECT);
			saveOrderLog(user, order.getOrderid(), "拒绝退货("+opinion+")");
			
		}
		returnOrderMapper.updateOrderStatus(order);
	}

	@Transactional
	public void submitPickUpOrderLogisticsInfo(ReturnOrder order,
			OrderLogistics logistics,UserInfo user) {
		order.setStatus(StaticUtils.RETURN_ORDER_PICKUP);		
		returnOrderMapper.updateOrderStatus(order);
		logistics.setId(StringUtil.getUUID());
		logisMapper.insert(logistics);
		saveOrderLog(user, order.getOrderid(), "配送员即将上门取货");
	}

	@Transactional
	public void pickupReturnOrder(ReturnOrder order,UserInfo user) {
		order.setStatus(StaticUtils.RETURN_ORDER_PROCESSING);		
		returnOrderMapper.updateOrderStatus(order);
		saveOrderLog(user, order.getOrderid(), "配送员已上门取货");
	}

	@Transactional
	public void receiveReturnGoods(ReturnOrder order,UserInfo user) {
		returnOrderMapper.updateRealReturnQty(order);
		OrderDetail detail = new OrderDetail();
		detail.setDetailid(order.getRelOrderDetailid());
		detail.setReturnQty(order.getCount());
		detail.setReturnRealQty(order.getReceiveQty());
		buyOrderDetailMapper.updateOrderDetail(detail);
		saveOrderLog(user, order.getOrderid(), "退货完成");
	}

	public ReturnOrder getReturnOrderDetailInfo(String orderid) {
		ReturnOrder order = returnOrderMapper.selectOrderInfoById(orderid);
		if(order!=null){
			order.setLog(logMapper.selectOrderLogList(orderid));
			order.setLogistics(logisMapper.selectListByOrderid(orderid));
		}
		return order;
	}
	
	public ReturnOrder getReturnOrderSimpleInfo(String orderid){
		return returnOrderMapper.selectOrderInfoById(orderid);
	}


	public PageInfo<ReturnOrder> getOrderListForSeller(Map<String, Object> param,
			int page, int rows) {
		PageHelper.startPage(page,rows); 
        List<ReturnOrder> msgList = returnOrderMapper.getOrderListForSeller(param);
        PageInfo<ReturnOrder> info = new PageInfo<ReturnOrder>(msgList);
        return info;
	}


	public PageInfo<ReturnOrder> getOrderListForBuyerWithSummary(
			Map<String, Object> param, int page, int rows) {
		PageHelper.startPage(page,rows); 
        List<ReturnOrder> msgList = returnOrderMapper.getOrderListForBuyerWithSummary(param);
        PageInfo<ReturnOrder> info = new PageInfo<ReturnOrder>(msgList);
        
        for(ReturnOrder order : info.getList()){
        	order.setOrderStatus(getReturnOrderStatusByBuyOrderId(order.getOrderid(),order.getOrgSkuid()));
        }
        return info;
	}
	
	
	private String[] getReturnOrderStatusByBuyOrderId(String buyOrderid,String orgSkuid){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("buyOrderid", buyOrderid);
		param.put("orgSkuid", orgSkuid);
		return returnOrderMapper.getReturnOrderStatusByBuyOrderid(param);
	}


	public List<ReturnOrder> getReturnOrderListByBuyOrderid(String buyOrderid,String orgskuid) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("buyOrderid", buyOrderid);
		param.put("orgSkuid", orgskuid);
		List<ReturnOrder> list = returnOrderMapper.getReturnOrderListByBuyOrderid(param);
		for(ReturnOrder order:list){
			order.setLog(logMapper.selectOrderLogList(order.getOrderid()));
			order.setLogistics(logisMapper.selectListByOrderid(order.getOrderid()));
		}
		return list;
	}


}

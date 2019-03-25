package com.yj.hqbz.services.impl.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.order.OrderDetailMapper;
import com.yj.hqbz.mapper.order.PurchaseDetailMapper;
import com.yj.hqbz.mapper.order.PurchaseIndexMapper;
import com.yj.hqbz.model.order.OrderDetail;
import com.yj.hqbz.model.order.PurchaseDetail;
import com.yj.hqbz.model.order.PurchaseIndex;
import com.yj.hqbz.model.order.PurchaseTask;
import com.yj.hqbz.model.order.ReturnOrder;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.order.PurchaseService;
import com.yj.hqbz.util.DateUtil;
import com.yj.hqbz.util.StringUtil;

/**  
 * @Title: PurchaseServiceImpl.java
 * @Package com.yj.hqbz.services.impl.order
 * @Description: TODO
 * @author xx
 * @date 2019-3-9
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Resource
	PurchaseIndexMapper indexMapper;
	@Resource
	PurchaseDetailMapper detailMapper;
	@Resource
	OrderDetailMapper buyOrderDetailMapper;
	
	
	//======================================采购任务============================
	/**
	 * 获取采购任务列表
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<PurchaseTask> getPurchaseTask(Map<String, Object> param,
			int page, int rows) {
		PageHelper.startPage(page,rows); 
        List<PurchaseTask> msgList = indexMapper.getPurchaseTaskList(param);
        PageInfo<PurchaseTask> info = new PageInfo<PurchaseTask>(msgList);
        for(PurchaseTask task:info.getList()){
        	task.setOrderDetailid(StringUtil.getStrByList(indexMapper.getPurchaseTaskOrderDetailsId(param)));
        }
        return info;
	}	
	
	/**
	 * 采购任务生成采购单,首先根据所选参数，计算出有选中的商家有哪些，在根据商家进行分别查询汇总
	 * 
	 */
	@Transactional
	public void createPurchaseBill(List<PurchaseTask> taskList,UserInfo user) {
		String detailId = "";
		String skuId 	= "";
		for(PurchaseTask task:taskList){
			detailId += task.getOrderDetailid()+",";
			skuId 	+= task.getOrgSkuid()+",";
		}
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("detailIdList", detailId.split(","));		
		
		//取得要生成采购任务的商家信息
		List<Integer> orgList = indexMapper.getPurchaseTaskOrderSellerOrganizationBySelectedItem(param);
		param.put("skuIdList", skuId.split(","));
		
		for(Integer orgid:orgList){
			param.put("orgid", orgid);
			
			
			List<PurchaseTask> orgTask = indexMapper.getPurchaseTaskOrderDetailsBySelectedItem(param);
			//生成采购单
			PurchaseIndex index = new PurchaseIndex();
			index.setCaretorid(user.getUserid());
			index.setCreateDate(new Date());
			index.setCreator(user.getTrueName());
			index.setOrgid(orgid);
			index.setOrderid(StringUtil.getUUID());
			
			index.setOrderNo("C"+DateUtil.getStrByDate(new Date(), "yyyyMMddHHmmss")+StringUtil.getRandomNum(4));
			index.setTaskCount(orgTask.size());
			BigDecimal total = new BigDecimal(0);
			
			indexMapper.insert(index);
			for(PurchaseTask taskItem:orgTask){
				PurchaseDetail detail = new PurchaseDetail();
				detail.setDetailid(StringUtil.getUUID());
				detail.setIndexid(index.getOrderid());
				detail.setSkuid(taskItem.getSkuid());
				detail.setSepcInfo(taskItem.getSpecInfo());
				detail.setOrgSkuid(taskItem.getOrgSkuid());
				detail.setAuixRate(taskItem.getAuixRate());
				detail.setAuixUnit(taskItem.getAuixUnit());
				detail.setQty(taskItem.getQty());
				detail.setQtyBasic(taskItem.getQtyBasic());
				detail.setPlanQty(detail.getQtyBasic());
				detail.setRealQty(detail.getQtyBasic());				
				detail.setPrice(taskItem.getPriceBasic());
				detail.setRealPrice(detail.getPrice());
				detail.setStatus(0);
				total = total.add(taskItem.getQtyBasic().multiply(taskItem.getPriceBasic()).setScale(2,BigDecimal.ROUND_UP));
				detailMapper.insert(detail);				
			}
			index.setTotal(total);
			indexMapper.updateByPrimaryKeySelective(index);
			
			for(String orderDetailId:detailId.split(",")){
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setDetailid(orderDetailId);
				orderDetail.setCreatePurchaseOrder(1);
				buyOrderDetailMapper.updateOrderDetail(orderDetail);
			}
			
		}

	}
	
	//=====================================采购单===================================
	
	public PageInfo<PurchaseIndex> getPurchaseList(Map<String, Object> param,
			int page, int rows) {
		PageHelper.startPage(page,rows); 
        List<PurchaseIndex> msgList = indexMapper.selectBillList(param);
        PageInfo<PurchaseIndex> info = new PageInfo<PurchaseIndex>(msgList);
        return info;
	}

	@Transactional
	public void deletePurchaseItem(String orderid,String itemId) {
		detailMapper.deleteByPrimaryKey(itemId);
	}

	public PurchaseIndex getPurchaseBillDetailInfo(String orderid) {
		PurchaseIndex index = indexMapper.selectByPrimaryKey(orderid);
		if(index != null){
			index.setDetail(detailMapper.selectListByIndexid(orderid));
		}
		return index;
	}

	@Transactional
	public void updateAndSubmitPurchaseBill(PurchaseIndex index,UserInfo user) {
		List<PurchaseDetail> list = index.getDetail();
		for(PurchaseDetail item:list){
			detailMapper.update(item);
		}
		index.setTotal(null);
		index.setTaskCount(null);
		index.setStatus(1);
		index.setSubmitDate(new Date());
		index.setUserid(user.getUserid());
		index.setUsername(user.getTrueName());
		indexMapper.updateByPrimaryKeySelective(index);
	}

	

}

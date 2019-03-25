package com.yj.hqbz.services.order;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.order.PurchaseIndex;
import com.yj.hqbz.model.order.PurchaseTask;
import com.yj.hqbz.model.user.UserInfo;

/**  
 * @Title: 采购任务
 * @Package com.yj.hqbz.services.order
 * @Description: TODO
 * @author xx
 * @date 2019-3-9
 */
public interface PurchaseService {
	
	//获取采购任务列表
	PageInfo<PurchaseTask> getPurchaseTask(Map<String, Object> param,
			int page, int rows);
	
	
	//获取采购单列表
	PageInfo<PurchaseIndex> getPurchaseList(Map<String,Object> param,int page,int rows);
	
	//根据采购单明细ID删除采购任务
	void deletePurchaseItem(String orderid,String itemId);
	
	//根据采购单ID获取采购详情
	PurchaseIndex getPurchaseBillDetailInfo(String orderid);
	//修改提交采购单
	void updateAndSubmitPurchaseBill(PurchaseIndex index,UserInfo user);
	//生成采购单
	void createPurchaseBill(List<PurchaseTask> taskList,UserInfo user);
}

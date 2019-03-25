package com.yj.hqbz.mapper.order;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.order.PurchaseIndex;
import com.yj.hqbz.model.order.PurchaseTask;

public interface PurchaseIndexMapper {
	
	//获取采购任务列表
    List<PurchaseTask> getPurchaseTaskList(Map<String,Object> param);    
    //根据采购任务明细ID获取该明细对应的购货订单明细ＩＤ
    List<String> getPurchaseTaskOrderDetailsId(Map<String,Object> param);
    
    //根据选择的采购任务获取对应的商家信息
    List<Integer> getPurchaseTaskOrderSellerOrganizationBySelectedItem(Map<String,Object> param);
    //根据选择的采购任务获取对应的采购明细
    List<PurchaseTask> getPurchaseTaskOrderDetailsBySelectedItem(Map<String,Object> param);
    

    int insert(PurchaseIndex record);

    PurchaseIndex selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PurchaseIndex record);
    
    List<PurchaseIndex> selectBillList(Map<String,Object> param);
    
    
}
package com.yj.hqbz.services.inventory;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.inventory.BVoucherIndex;
import com.yj.hqbz.model.inventory.Inventory;
import com.yj.hqbz.model.order.OrderDetail;
import com.yj.hqbz.model.order.OrderForm;

public interface InventoryService {
	Inventory selectByPrimaryKey(String id);
	
	Inventory getStockById(String id);
	
	PageInfo<OrderForm> getNotInStoreOrderBill(Map<String,Object> param,int page,int rows);
	
	PageInfo<Map<String,Object>> getOrderDetail(String orderid,int page,int rows);
	
	PageInfo<Inventory> getStockBill(Map<String,Object> param,int page,int rows);
	
	PageInfo<Inventory> getStockBatch(Map<String,Object> param,int page,int rows);
	
	void addInventory(List<Inventory> stocks,BVoucherIndex index);
	
	void outStock(BVoucherIndex index);
	
	PageInfo<Map<String,Object>> getOutInDetail(String id,int page,int rows);
	
}

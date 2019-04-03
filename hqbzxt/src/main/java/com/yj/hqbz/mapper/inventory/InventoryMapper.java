package com.yj.hqbz.mapper.inventory;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.inventory.Inventory;
import com.yj.hqbz.model.order.OrderDetail;
import com.yj.hqbz.model.order.OrderForm;

public interface InventoryMapper {
    Inventory selectByPrimaryKey(String id);
    
    Inventory getStockById(String id);
    
    List<OrderForm> getNotInStoreOrderBill(Map<String,Object> param);
    
    List<Map<String,Object>> getOrderDetail(String orderid);
    
    List<Inventory> getStockBill(Map<String,Object> param);
    
    List<Inventory> getStockBatch(Map<String,Object> param);
    
    int addInventory(Inventory stock);
    
    int outStock(Inventory stock);
    
    int updateOrderInStockDate(String detailid);
    
    int updateSporadicInStockDate(String indexid);
}
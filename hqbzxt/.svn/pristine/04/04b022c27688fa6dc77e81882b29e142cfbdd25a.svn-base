package com.yj.hqbz.mapper.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.order.ReturnOrder;

public interface ReturnOrderMapper {
	
	/**根据退货订单ID获取订单详情**/
    ReturnOrder selectOrderInfoById(String orderid);
    
    /**根据购货订单明细ID获取该明细可退货数量**/
    BigDecimal selectReturnQtyCountByBuyOrderDetailId(String relOrderDetailid);
    
    /**写入退货订单**/
    void insertReturnOrder(ReturnOrder order);
    /**修改退货订单状态**/
    void updateOrderStatus(ReturnOrder order);
    /**修改退货订单实际收货数量**/
    void updateRealReturnQty(ReturnOrder order);
    
    /**根据购货订单ID获取相应的退货订单**/
    List<ReturnOrder> getReturnOrderListByBuyOrderid(Map<String,Object> param);
    /**根据购货订单ID获取相应的退货订单状态**/
    String[] getReturnOrderStatusByBuyOrderid(Map<String,Object> param);
     
    /**获取退货订单(卖家使用)**/
    List<ReturnOrder> getOrderListForSeller(Map<String,Object> param);
    
    /**获取退货订单（买家汇总)**/
    List<ReturnOrder> getOrderListForBuyerWithSummary(Map<String,Object> param);
    
}
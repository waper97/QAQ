package com.yj.hqbz.mapper.order;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.order.OrderForm;

public interface OrderFormMapper {
    OrderForm selectByPrimaryKey(String id);
    //添加订单
    void addOrderForm(OrderForm of);
    //更新订单
    void updateOrder(OrderForm of);
    //获取用户订单列表
    List<OrderForm> getBuyerOrderListByUser(Map<String,Object> map);
    //删除订单
    void deleteOrder(OrderForm of);
    //获取订单详情订单
    OrderForm getOrderForDetail(String id);
    //根据订单明细ID获取订单
    OrderForm getOrderFormByDetailid(String detailid);
    //根据用户获取订单总额及消费次数
    OrderForm getOrderCountByUser(String userid);
    OrderForm getOrderRetrunTotal(String userid);
    
    /***********************卖家*******************/
    //卖家查询订单
    List<OrderForm> getOrderListForSeller(Map<String,Object> map);
    //更新收货信息
    void updateReceiver(Map<String,Object> map);
    //得到当前订单还未完成溯源的数量
    int getNotTraceNumByOrderid(String orderid);
    //根据订单ID获取出库总额及成本总额
    OrderForm getOutTotalAndCostTotalByOrderid(String orderid);
    //根据订单ID获取实收总额
    OrderForm getReceiveTotalByOrderid(String orderid);
    
    /******************配送员*************************/
    //获取待收货列表及差异列表
    List<OrderForm> getReceiveListForDistributor(Map<String,Object> map);
    
    
    
}
package com.yj.hqbz.services.order;

import java.util.List;

import com.yj.hqbz.model.order.OrderLog;

public interface OrderLogService {

    //根据订单/退货单ID查询对应日志
    List<OrderLog> getOrderLogByOrderId(String orderid);
    //写订单/退货单日志
    void addOrderLog(OrderLog log);
}

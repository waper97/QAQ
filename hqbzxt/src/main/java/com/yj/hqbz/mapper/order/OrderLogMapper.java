package com.yj.hqbz.mapper.order;

import java.util.List;

import com.yj.hqbz.model.order.OrderLog;

public interface OrderLogMapper {
    int insert(OrderLog record);

    List<OrderLog> selectOrderLogList(String orderid);
}
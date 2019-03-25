package com.yj.hqbz.mapper.order;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.order.OrderLogistics;

public interface OrderLogisticsMapper {
	
    List<OrderLogistics> selectListByOrderid(String orderid);
    
    void insert(OrderLogistics record);
    
    void updateBySelective(OrderLogistics record);
    
    void updateByMap(Map<String,Object> map);
}
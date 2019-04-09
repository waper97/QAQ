package com.yj.hqbz.services.impl.order;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yj.hqbz.mapper.order.OrderLogMapper;
import com.yj.hqbz.model.order.OrderLog;
import com.yj.hqbz.services.order.OrderLogService;

@Service
public class OrderLogServiceImpl implements OrderLogService {

    @Resource
    OrderLogMapper orderLogMapper;
    
    
    public List<OrderLog> getOrderLogByOrderId(String orderid) {
        return orderLogMapper.selectOrderLogList(orderid);
    }

    
    @Transactional
    public void addOrderLog(OrderLog log) {
       orderLogMapper.insert(log);
    }

}

package com.yj.hqbz.mapper.order;


import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.order.OrderDetail;

public interface OrderDetailMapper {
	
    OrderDetail selectByPrimaryKey(String detailid);
    //添加订单明细
    void addOrderDetail(OrderDetail detail);
  //根据购物车明细ID获取订单所需数据
    List<OrderDetail> getCartListForOrder(Map<String,Object> map);
    //更新订单明细
    void updateOrderDetail(OrderDetail detail);
    //根据商品ID获取直接购买数据
    OrderDetail getBuyNowInfoForOrder(Integer orgSkuid);
    //订单列表的详情
    List<OrderDetail> getDetailListByOrderid(String orderid);
    //订单详情的详情
    List<OrderDetail> getOrderDetailByOrderid(String orderid);
    //根据订单ID获取详情数量
    List<OrderDetail> getDetailQtyByOrderid(String orderid);
    //根据订单ID获取明细的未确认差异数量
    Integer getDiffDetailNumByOrderid(String orderid);
    //获取商品分拣列表
    List<OrderDetail> getTraceByGoods(Map<String,Object> map);
    //商品分拣相信
    List<OrderDetail> getTraceGoodsDetail(Map<String,Object> map);
    //根据溯源ID获取该商品的订单明细
    OrderDetail getOrderDetailByTraceid(String traceid);
    
}
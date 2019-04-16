package com.yj.hqbz.services.order;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.order.OrderDetail;
import com.yj.hqbz.model.order.OrderForm;
import com.yj.hqbz.model.order.OrderLogistics;
import com.yj.hqbz.model.trace.GoodsTrace;
import com.yj.hqbz.model.user.UserInfo;


public interface OrderFormService {
    //根据ID获取订单
    OrderForm getOrderFormById(String orderid);
    //创建订单
    List<String> createOrder(UserInfo user,String addressid,List<Map> cartList);
    //获取订单数据
    List<List<OrderDetail>> getOrderData(String userid,String gcid,Integer orgid);
    //创建直接购买订单
    List<String> createBuyNowOrder(UserInfo user,String addressid,List<Map> cartList);
    //订单列表
    PageInfo<OrderForm> getOrderListByUser(String userid,Integer orderStatus,int page,int rows);
    //订单详细
    //List<Map<String,Object>> getDetailList(String orderid);
    //取消订单
    void cancelOrderForBuyer(OrderForm of,UserInfo user);
    //删除订单
    void deleteOrder(OrderForm of);
    //根据订单ID获取订单详情
    OrderForm getOrderDetail(String id);
    //取消订单中的商品
    int cancelGoodsForBuyer(OrderForm of,UserInfo user,String detailid);
    //根据订单明细ID获取订单数据
    OrderForm getOrderFormByDetailid(String detailid);
    //买家收货
    void receiveGoodsForBuyer(List<Map> goodsList,UserInfo user,String orderid);
    //获取用户订单汇总信息
    OrderForm getOrderSummary(String userid);
    
    /********************************卖家****************************************/
    //卖家确定订单
    void confirmOrderForSeller(OrderForm of,Integer status,String reason,UserInfo user);
    //卖家取消订单
    void cancelOrderForSeller(OrderForm of,UserInfo user,String reason);
    //卖家批量付款
    void payForSeller(List<OrderForm> orderList);
    //卖家发货
    int deliverForSeller(OrderLogistics deliver,UserInfo user);
    //卖家确认差异
    int confirmDiffForSeller(Map<String,Object> map,UserInfo user);
    //根据订单ID获取差异明细
    List<OrderDetail> getDiffDetailByOrderid(String orderid);
    //卖家查询订单列表
    PageInfo<OrderForm> getOrderListForSeller(Map<String,Object> map,int page,int rows);
    //根据订单ID获取商品详情
    List<OrderDetail> getOrderDetailForSeller(String orderid);
    //修改订单物流及收货人信息
    void saveOrderForSeller(String addressInfo,String deliverInfo);
    //添加溯源信息
    void addGoodsTrace(GoodsTrace trace);
    //获取溯源信息
    GoodsTrace getGoodsTraceByOrderDetailid(String orderDetailid);
    //完成溯源
    int traceComplete(OrderForm o,UserInfo user);
    //更新分拣数量
    int updateTraceCount(GoodsTrace trace,UserInfo user);
    //获取商品分拣列表
    PageInfo<OrderDetail> getTraceByGoods(Map<String,Object> map,int page,int rows);
    
    /******************************配送员****************************************/
   //配送员确认差异
    int confirmDiffForDistributor(Map<String,Object> map,UserInfo user);
    //配送员获取待收货列表
    PageInfo<OrderForm> getReceiveListForDistributor(Map<String,Object> map,int page,int rows);
    
    /************************其他***********************************************/
    //根据溯源ID获取该商品的订单明细详情
    OrderDetail getOrderDetailInfoByTraceId(String traceId);
}
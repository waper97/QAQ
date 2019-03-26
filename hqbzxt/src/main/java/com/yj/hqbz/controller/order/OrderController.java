package com.yj.hqbz.controller.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.hqbz.model.order.OrderDetail;
import com.yj.hqbz.model.order.OrderForm;
import com.yj.hqbz.model.order.OrderLogistics;
import com.yj.hqbz.model.trace.GoodsTrace;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.order.OrderFormService;
import com.yj.hqbz.services.order.OrderStatusConst;
import com.yj.hqbz.util.CommUtil;

@RestController
public class OrderController extends BaseController {

    @Resource
    OrderFormService orderService; 
    /**
     * 买家购物车列表
     * @param orderStatus
     * @param grid
     * @return
     */
    @GetMapping("/order/buyer/getList")
    public Object getOrderListForBuyer(String orderStatus,DataGridModel grid){
        UserInfo user = getTokenUser();
        Integer status = null;
        if(StringUtils.isNotBlank(orderStatus)){
            status = CommUtil.null2Int(orderStatus);
        }
        PageInfo<OrderForm> pageList =orderService.getOrderListByUser(user.getUserid(), status, grid.getPage(), grid.getRows());
        return new BaseRes("获取订单列表成功!",pageList.getTotal(),pageList.getPages(),pageList.getList());
    }
    /**
     * 买家取消订单
     * @param id
     * @return
     */
    @PostMapping("/order/buyer/cancel")
    public Object cancelOrderForBuyer(String id){
        UserInfo user = getTokenUser();
        if(StringUtils.isBlank(id)){
            return fail("参数错误！");
        }
        String[] ids = id.split(",");
        for(String ofid:ids){
            OrderForm order = orderService.getOrderFormById(ofid);
            if(order==null||order.getOrderStatus()!=OrderStatusConst.NOT_CONFIRM){
                return fail("订单无效!");
            }
            order.setOrderStatus(OrderStatusConst.BUYER_CANCEL);
            orderService.cancelOrderForBuyer(order,user);
        }        
        return success("订单取消成功！");
        
    }
    /**
     * 买家删除订单
     * @param id
     * @return
     */
    @PostMapping("/order/buyer/delete")
    public Object deleteOrderForBuyer(String id){
        if(StringUtils.isBlank(id)){
            return fail("参数错误！");
        }
        String[] ids = id.split(",");
        for(String ofid:ids){
            OrderForm order = orderService.getOrderFormById(ofid);
            if(order==null||(order.getOrderStatus()!=OrderStatusConst.BUYER_CANCEL&&order.getOrderStatus()!=OrderStatusConst.SELLER_CANCEL)){
                return fail("订单无效!");
            }
            orderService.deleteOrder(order);
        }
        
        return success("订单删除成功！");
    }
    /**
     * 获取订单详情
     * @param id
     * @return
     */
    @GetMapping("/order/common/getOrderInfo")
    public Object getOrderDetailForBuyer(String id){
        if(StringUtils.isBlank(id)){
            return fail("参数错误！");
        }
        OrderForm order = orderService.getOrderFormById(id);
        if(order == null){
            return fail("订单无效!"); 
        }
        return success(orderService.getOrderDetail(id));
        
    }
    /**
     * 买家取消商品
     * @param detailid
     * @return
     */
    @PostMapping("/order/buyer/cancelGoods")
    public Object cancelGoodsForBuyer(String detailid){
        UserInfo user = getTokenUser();
        if(StringUtils.isBlank(detailid)){
            return fail("参数错误！");
        }
        String[] ids = detailid.split(",");
        for(String id:ids){
            OrderForm of = orderService.getOrderFormByDetailid(id);
            if(of==null||of.getOrderStatus()!=OrderStatusConst.NOT_CONFIRM){
                return fail("无效订单！");
            }       
            if(orderService.cancelGoodsForBuyer(of,user,id)<0){
                return fail("最后一个商品不能取消，只能取消订单！");
            }
        }
       
        return success("取消商品成功！");
    }
    /**
     * 买家收货
     * @param orderid
     * @param goodsInfo
     * @return
     */
    @PostMapping("/order/buyer/receiveGoods")
    public Object receiveGoodsForBuyer(String orderid,String goodsInfo){
        UserInfo user = getTokenUser();
        if(StringUtils.isBlank(goodsInfo)||StringUtils.isBlank(orderid)){
            return fail("参数错误！");
        }
        List<Map> goodsList = JSONArray.parseArray(goodsInfo, Map.class);
        if(goodsList.size()<=0){
            return fail("参数错误！");
        }
        //检查订单状态
        for(Map<String,Object> map:goodsList){
            String detailid = CommUtil.null2String(map.get("detailid"));
            OrderForm of = orderService.getOrderFormByDetailid(detailid);
            if(of==null||of.getOrderStatus()!=OrderStatusConst.WAIT_RECEVIE||!of.getId().equals(orderid)){
                return fail("详情数据与订单不一致！");
            }
        }
        orderService.receiveGoodsForBuyer(goodsList,user,orderid);
        return success("确认收货成功!");
        
    }
    
    /*********************************卖家**************************************************/
    /**
     * 商家确认订单
     * @param orderid
     * @param status
     * @param reason
     * @return
     */
    @PostMapping("/order/seller/confirmOrder")
    public Object confirmOrderForSeller(String orderid,Integer status,String reason){
        UserInfo user = getTokenUser();
        if(StringUtils.isBlank(orderid)||status==null||(status!=1&&status!=2)){
            return fail("参数错误！");
        }
        OrderForm order = orderService.getOrderFormById(orderid);
        if(order == null||order.getOrderStatus()!=OrderStatusConst.NOT_CONFIRM){
            return fail("订单无效!"); 
        }
        orderService.confirmOrderForSeller(order, status, reason, user);
        return success("操作订单成功！");
    }
    /**
     * 商家取消订单
     * @param orderid
     * @return
     */
    @PostMapping("/order/seller/cancelOrder")
    public Object cancelOrderForSeller(String orderid,String reason){
        UserInfo user = getTokenUser();
        if(StringUtils.isBlank(orderid)){
            return fail("参数错误！");
        }
        OrderForm order = orderService.getOrderFormById(orderid);
        if(order == null||order.getOrderStatus()==OrderStatusConst.HAVE_RECEIVE){
            return fail("订单无效！"); 
        }
        orderService.cancelOrderForSeller(order, user,reason);
        return success("取消订单成功！");
    }
    /**
     * 付款设置
     * @param orderid
     * @return
     */
    @PostMapping("/order/seller/pay")
    public Object payForSeller(String orderid){
        getTokenUser();
        if(StringUtils.isBlank(orderid)){
            return fail("参数错误！");
        }
        List<OrderForm> orderList = new ArrayList<OrderForm>();
        String[] ids = orderid.split(",");
        for (String id : ids) {
            OrderForm of = orderService.getOrderFormById(id);           
            if (of == null
                    || of.getOrderStatus() != OrderStatusConst.HAVE_RECEIVE
                    || of.getPayStatus() == OrderStatusConst.PAY_STATUS.HAVE_PAY
                            .ordinal()) {
                return fail("包含无效订单!");
            }
            orderList.add(of);
        }
        orderService.payForSeller(orderList);
        return success("付款成功！");
    }
    /**
     * 确认发货
     * @param deliver
     * @return
     */
    @PostMapping("/order/seller/deliver")
    public Object deliverForSeller(OrderLogistics deliver){
        UserInfo user = getTokenUser();
        if(orderService.deliverForSeller(deliver, user)<0){
            return fail("订单状态不能发货！");
        }
        return success("发货成功！");
    }
    /**
     * 确认差异
     * @param orderid
     * @param detailid
     * @param status
     * @param reason
     * @return
     */
    @PostMapping("/order/seller/confirmDiff")
    public Object confirmDiffForSeller(String orderid,String detailid,Integer status,String reason){
        UserInfo user = getTokenUser();
        if(StringUtils.isBlank(orderid)||StringUtils.isBlank(detailid)||status==null||(status!=1&&status!=2)){
            return fail("参数错误！");
        }
        OrderForm of = orderService.getOrderFormByDetailid(detailid);        
        if(of==null||!of.getId().equals(orderid)||of.getOrderStatus()!=OrderStatusConst.CONFIRM_DIFF){
            return fail("无效订单！");
        }
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("orderid", orderid);
        params.put("detailid", detailid);
        params.put("status", status);
        params.put("reason", reason);
        if(orderService.confirmDiffForSeller(params, user)<0){
            return fail("等待配送员确认后才能确认！");
        }
        return success("确认差异成功！");
    }
    /**
     * 获取差异列表
     * @param orderid
     * @return
     */
    @GetMapping("/order/seller/getOrderDiffGoodsList")
    public Object getDiffDetailForSeller(String orderid){
        getTokenUser();
        if(StringUtils.isBlank(orderid)){
            return fail("参数错误！");
        }
        OrderForm of = orderService.getOrderFormById(orderid);
        if(of==null||of.getOrderStatus()!=OrderStatusConst.CONFIRM_DIFF){
            return fail("无效订单！");
        }
        return success(orderService.getDiffDetailByOrderid(orderid));
    }
    /**
     * 获取卖家订单
     * @param map
     * @param grid
     * @return
     */
    @GetMapping("/order/seller/getList")
    public Object getOrderListForSeller(@RequestParam Map<String,Object> map,DataGridModel grid){
        getTokenUser();
        PageInfo<OrderForm> pageList = orderService.getOrderListForSeller(map, grid.getPage(), grid.getRows());
        return new BaseRes("获取列表成功！",pageList.getTotal(),pageList.getPages(),pageList.getList());
    }
    /**
     * 获取订单详情
     * @param orderid
     * @return
     */
    @GetMapping("/order/seller/getOrderGoodsList")
    public Object getOrderDetailForSeller(String orderid){
        getTokenUser();
        if(StringUtils.isBlank(orderid)){
            return fail("参数无效！");
        }
        return success(orderService.getOrderDetailForSeller(orderid));
    }
    /**
     * 修改订单内容
     * @param addressInfo
     * @param deliverInfo
     * @return
     */
    @PostMapping("/order/seller/saveOrder")
    public Object saveOrderForSeller(String addressInfo,String deliverInfo){
        getTokenUser();
        if(StringUtils.isBlank(deliverInfo)){
            return fail("参数错误！");
        }
        orderService.saveOrderForSeller(addressInfo, deliverInfo);
        return success("保存成功！");
    }
    /**
     * 保存溯源信息
     * @param trace
     * @return
     */
    @PostMapping("/order/seller/saveGoodsSource")
    public Object saveGoodsSource(@RequestBody GoodsTrace trace){
        getTokenUser();
        if(StringUtils.isBlank(trace.getOrderDetailid())){
            return fail("参数错误！");
        }
        String[] ids = trace.getOrderDetailid().split(",");
        for(String id:ids){
            trace.setOrderDetailid(id);
            orderService.addGoodsTrace(trace);
        }        
        return success("添加溯源成功！");
    }
    /**
     * 根据商品明细ID获取溯源信息
     * @param orderDetailid
     * @return
     */
    @GetMapping("/order/common/getOrderGoodsSource")
    public Object getOrderGoodsSource(String orderDetailid){
        getTokenUser();
        if(StringUtils.isBlank(orderDetailid)){
            return fail("参数错误");
        }
        return success(orderService.getGoodsTraceByOrderDetailid(orderDetailid));
    }
    /**
     * 完成溯源后，更新订单状态，完成溯源
     * @param id
     * @return
     */
    @PostMapping("/order/seller/submitOrderSource")
    public Object submitOrderSource(@RequestBody OrderForm of){
        UserInfo user = getTokenUser();
        if(StringUtils.isBlank(of.getId())){
            return fail("参数错误");
        } 
        int rst = orderService.traceComplete(of,user);
        if(rst == -1){            
            return fail("请完善溯源信息！");
        }
        if(rst == -2){
            return fail("无效订单！");
        }
        return success("更新溯源信息成功！");
    }
    /**
     * 更新溯源商品数量
     * @param trace
     * @return
     */
    @PostMapping("/order/seller/updateSourceCount")
    public Object updateSourceCount(GoodsTrace trace){
        UserInfo user = getTokenUser();
        if(StringUtils.isBlank(trace.getOrderDetailid())){
            return fail("参数错误");
        }
        if(orderService.updateTraceCount(trace,user)<0){
            return fail("请完善溯源信息！");
        }
        return success("修改成功！");
        
    }
    /**
     * 根据商品溯源
     */
    @GetMapping("/order/seller/traceByGoods")
    public Object traceByGoods(@RequestParam Map<String,Object> map,DataGridModel grid){
        getTokenUser();
        PageInfo<OrderDetail> pageList = orderService.getTraceByGoods(map, grid.getPage(), grid.getRows());
        return new BaseRes("获取列表成功!",pageList.getTotal(),pageList.getPages(),pageList.getList());
    }
    /**************************************************配送员**************************************/
    /**
     * 配送员确认差异
     * @param detailid
     * @param status
     * @param reason
     * @return
     */
    @PostMapping("/order/distributor/confimDiff")
    public Object confirmDiffForDistributor(String detailid,Integer status,String reason){
        UserInfo user = getTokenUser();
        OrderForm of = orderService.getOrderFormByDetailid(detailid);        
        if(of==null||of.getOrderStatus()!=OrderStatusConst.CONFIRM_DIFF){
            return fail("无效订单！");
        }
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("detailid", detailid);
        params.put("status", status);
        params.put("reason", reason);
        if(orderService.confirmDiffForDistributor(params, user)<0){
            return fail("差异状态不正确！");
        }
        return success("差异确认成功！");
    }
    
    
}

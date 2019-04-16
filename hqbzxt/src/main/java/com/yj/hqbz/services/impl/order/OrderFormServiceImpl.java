package com.yj.hqbz.services.impl.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.goods.OrgSkuMapper;
import com.yj.hqbz.mapper.order.OrderDetailMapper;
import com.yj.hqbz.mapper.order.OrderFormMapper;
import com.yj.hqbz.mapper.order.OrderLogisticsMapper;
import com.yj.hqbz.mapper.system.MessageMapper;
import com.yj.hqbz.mapper.trace.GoodsTraceMapper;
import com.yj.hqbz.mapper.trace.GoodsTracePicMapper;
import com.yj.hqbz.model.goods.OrgSku;
import com.yj.hqbz.model.order.OrderDetail;
import com.yj.hqbz.model.order.OrderForm;
import com.yj.hqbz.model.order.OrderLog;
import com.yj.hqbz.model.order.OrderLogistics;
import com.yj.hqbz.model.system.Message;
import com.yj.hqbz.model.trace.GoodsTrace;
import com.yj.hqbz.model.trace.GoodsTracePic;
import com.yj.hqbz.model.user.UserAddress;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.address.UserAddressService;
import com.yj.hqbz.services.order.GoodsCartService;
import com.yj.hqbz.services.order.OrderFormService;
import com.yj.hqbz.services.order.OrderLogService;
import com.yj.hqbz.services.order.OrderStatusConst;
import com.yj.hqbz.util.CommUtil;
import com.yj.hqbz.util.UUIDUtil;
import com.yj.hqbz.util.tmthreadpool.TmThreadPool;

@Service
public class OrderFormServiceImpl implements OrderFormService {

    @Resource
    OrderFormMapper orderMapper;
    @Resource 
    OrderDetailMapper detailMapper;
    @Resource
    OrderLogService logService;
    @Resource
    UserAddressService addService;
    @Resource
    GoodsCartService gcService;
    @Resource
    OrderLogisticsMapper logisticsMapper;
    @Resource
    OrgSkuMapper orgSkuMapper;
    @Resource
    GoodsTraceMapper traceMapper;
    @Resource
    GoodsTracePicMapper tracePicMapper;
    @Resource
    MessageMapper msgMapper;
    
    
    
    
    public OrderForm getOrderFormById(String orderid) {
        return orderMapper.selectByPrimaryKey(orderid);
    }
    
    
    public List<List<OrderDetail>> getOrderData(String userid, String gcid,Integer orgid) {
        List<String> gcList = Arrays.asList(gcid.split(","));
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userid", userid);
        params.put("gcList", gcList);  
        params.put("orgid", orgid);
        List<OrderDetail> detailList = detailMapper.getCartListForOrder(params);
        
        
        List<List<OrderDetail>> orderList = new ArrayList<List<OrderDetail>>();
        Map<Integer, List<OrderDetail>> attrGroupMap = new HashMap<Integer, List<OrderDetail>>();
        for (OrderDetail detail : detailList) {
            Integer attr = detail.getBusinessAttr();
            if (attrGroupMap.containsKey(attr)) {
                List<OrderDetail> oldList = attrGroupMap.get(attr);
                oldList.add(detail);
            } else {
                List<OrderDetail> newList = new ArrayList<OrderDetail>();
                newList.add(detail);
                attrGroupMap.put(attr, newList);
            }
        }
        
        Set<Entry<Integer,List<OrderDetail>>> attrEntrySet = attrGroupMap.entrySet();
        for(Entry<Integer,List<OrderDetail>> attrEntry:attrEntrySet){
          List<OrderDetail> newGroupList = attrEntry.getValue();
          orderList.add(newGroupList);
        }      
        return orderList;
    }
    
    
    @Transactional
    public List<String> createOrder(UserInfo user,String addressid,List<Map> cartList) {        
        UserAddress addr = addService.getAddrInfoById(CommUtil.null2Int(addressid));
        String areaName = addService.getAreaNameByAreaId(addr.getAreaid());
        List<String> orderNoList = new ArrayList<String>();
        for(Map<String,Object> map:cartList){
            String gcid = CommUtil.null2String(map.get("gcid"));
            Integer orgid = CommUtil.null2Int(map.get("orgid"));
            String msg = CommUtil.null2String(map.get("msg"));
            String expectRecvBeginTime = CommUtil.null2String(map.get("expectRecvBeginTime"));
            String expectRecvEndTime = CommUtil.null2String(map.get("expectRecvEndTime"));
            List<List<OrderDetail>> detailList = getOrderData(user.getUserid(), gcid, orgid);
            for(List<OrderDetail> list:detailList){
                OrderForm form = new OrderForm();
                String orderid = UUIDUtil.getUUID();
                form.setId(orderid);
                form.setOrderStatus(10);
                form.setMsg(msg);
                form.setExpectRecvBeginTime(CommUtil.formatDate(expectRecvBeginTime, "yyyy-MM-dd HH:mm:ss"));
                form.setExpectRecvEndTime(CommUtil.formatDate(expectRecvEndTime, "yyyy-MM-dd HH:mm:ss"));
                Integer businessAttr = list.get(0).getBusinessAttr();
                form.setOrderNo(CommUtil.formatTime("yyyyMMddHHmmss", new Date())+businessAttr+CommUtil.randomInt(4));
                form.setOrderType(businessAttr);
                form.setOrgid(user.getOrgId());
                form.setReceiver(addr.getReceiver());
                form.setReceiverAddress(areaName+addr.getAddress());
                form.setReceiverMobile(addr.getLinkPhone());
                form.setShipPrice(0);
                form.setStoreid(orgid);
                form.setUserid(user.getUserid());
                orderMapper.addOrderForm(form);
                BigDecimal total = new BigDecimal("0");
                for(OrderDetail detail:list){                   
                    detail.setDetailid(UUIDUtil.getUUID());
                    detail.setOrderid(orderid);
                    detail.setTraceStatus(0);
                    detailMapper.addOrderDetail(detail);  
                    //更新库存
                    this.subOrgSkuQty(detail.getQty(), CommUtil.null2Int(detail.getOrgSkuid()));
                    total=total.add(detail.getPrice().multiply(detail.getQty()));
                }
                form.setTotal(total);
                orderMapper.updateOrder(form);
                orderNoList.add(orderid);
                TmThreadPool.saveOrderLog(logService, orderid, user.getUserid(), user.getTrueName(), "用户【"+user.getTrueName()+"】下单成功");
            }     
            //删除购物车
            gcService.deleteGoodsCart(gcid);
        }
        return orderNoList;
    }

    private void subOrgSkuQty(BigDecimal count,Integer orgSkuid){
        OrgSku sku = orgSkuMapper.selectByPrimaryKey(orgSkuid);
        if(CommUtil.isNumeric(sku.getQty())){
            BigDecimal qty = BigDecimal.valueOf(CommUtil.null2Double(sku.getQty()));
            sku.setQty(CommUtil.null2String(qty.subtract(count)));
            orgSkuMapper.updateOrgSkuQtyBySkuId(sku);
        } 
    }
    private void addOrgSkuQty(BigDecimal count,Integer orgSkuid){
        OrgSku sku = orgSkuMapper.selectByPrimaryKey(orgSkuid);
        if(CommUtil.isNumeric(sku.getQty())){
            BigDecimal qty = BigDecimal.valueOf(CommUtil.null2Double(sku.getQty()));
            sku.setQty(CommUtil.null2String(qty.add(count)));
            orgSkuMapper.updateOrgSkuQtyBySkuId(sku);
        } 
    }
    
    
    @Transactional
    public List<String> createBuyNowOrder(UserInfo user, String addressid,
            List<Map> cartList) {
        UserAddress addr = addService.getAddrInfoById(CommUtil
                .null2Int(addressid));
        String areaName = addService.getAreaNameByAreaId(addr.getAreaid());
        List<String> orderNoList = new ArrayList<String>();
        Map<String, Object> cartMap = cartList.get(0);

        Integer orgSkuid = CommUtil.null2Int(cartMap.get("gcid"));
        Integer orgid = CommUtil.null2Int(cartMap.get("orgid"));
        String msg = CommUtil.null2String(cartMap.get("msg"));
        String expectRecvBeginTime = CommUtil.null2String(cartMap
                .get("expectRecvBeginTime"));
        String expectRecvEndTime = CommUtil.null2String(cartMap
                .get("expectRecvEndTime"));
        Long count = CommUtil.null2Long(cartMap.get("count"));
        OrderDetail detail = detailMapper.getBuyNowInfoForOrder(orgSkuid);

        // 创建订单
        OrderForm form = new OrderForm();
        String orderid = UUIDUtil.getUUID();
        form.setId(orderid);
        form.setOrderStatus(10);
        form.setMsg(msg);
        form.setExpectRecvBeginTime(CommUtil.formatDate(expectRecvBeginTime,
                "yyyy-MM-dd HH:mm:ss"));
        form.setExpectRecvEndTime(CommUtil.formatDate(expectRecvEndTime,
                "yyyy-MM-dd HH:mm:ss"));
        Integer businessAttr = detail.getBusinessAttr();
        form.setOrderNo(CommUtil.formatTime("yyyyMMddHHmmss", new Date())
                + businessAttr + CommUtil.randomInt(4));
        form.setOrderType(businessAttr);
        form.setOrgid(user.getOrgId());
        form.setReceiver(addr.getReceiver());
        form.setReceiverAddress(areaName + addr.getAddress());
        form.setReceiverMobile(addr.getLinkPhone());
        form.setShipPrice(0);
        form.setStoreid(orgid);
        form.setUserid(user.getUserid());
        orderMapper.addOrderForm(form);

        detail.setDetailid(UUIDUtil.getUUID());
        detail.setOrderid(orderid);
        detail.setTraceStatus(0);
        detail.setQty(BigDecimal.valueOf(count));
        detail.setQtyBasic(detail.getQtyBasic().multiply(
                BigDecimal.valueOf(count)));
        detailMapper.addOrderDetail(detail);
        //更新库存
        this.subOrgSkuQty(BigDecimal.valueOf(count), orgSkuid);

        form.setTotal(detail.getPrice().multiply(BigDecimal.valueOf(count)));
        orderMapper.updateOrder(form);
        orderNoList.add(orderid);
        TmThreadPool.saveOrderLog(logService, orderid, user.getUserid(),
                user.getTrueName(), "用户【"+user.getTrueName()+"】下单成功");

        return orderNoList;
    }

    
    public PageInfo<OrderForm> getOrderListByUser(String userid,
            Integer orderStatus, int page, int rows) {
        PageHelper.startPage(page,rows);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userid", userid);
        params.put("orderStatus", orderStatus);
        List<OrderForm> orderList = orderMapper.getBuyerOrderListByUser(params);
        PageInfo<OrderForm> info = new PageInfo<OrderForm>(orderList);
        for(OrderForm o:info.getList()){
            o.setOrderDetails(detailMapper.getDetailListByOrderid(o.getId()));
        }
        return info;
    }

    
    public void cancelOrderForBuyer(OrderForm of,UserInfo user) {
        orderMapper.updateOrder(of); 
        //更新库存
        List<OrderDetail> detailList = detailMapper.getDetailQtyByOrderid(of.getId());
        for(OrderDetail detail:detailList){
            this.addOrgSkuQty(detail.getQty(), CommUtil.null2Int(detail.getOrgSkuid()));
        }
        TmThreadPool.saveOrderLog(logService, of.getId(), user.getUserid(),
                user.getTrueName(), "用户【"+user.getTrueName()+"】取消订单");
        
    }

    
    public void deleteOrder(OrderForm of) {
        orderMapper.deleteOrder(of);        
    }

    
    public OrderForm getOrderDetail(String id) { 
        OrderForm order = orderMapper.getOrderForDetail(id);
        List<OrderLog> log = logService.getOrderLogByOrderId(id);
        List<OrderLogistics> logistics = logisticsMapper.selectListByOrderid(id);
        List<OrderDetail> detailList = detailMapper.getOrderDetailByOrderid(id);
        order.setOrderDetails(detailList);
        order.setLog(log);
        order.setLogistics(logistics);
        return order;
    }

    
    @Transactional
    public int cancelGoodsForBuyer(OrderForm of,UserInfo user,String detailid) {
        List<OrderDetail> detailList = detailMapper.getDetailListByOrderid(of.getId());
        if(detailList.size()<2){
            return -1;
        }
        
        //删除取消的订单明细
        OrderDetail detail = detailMapper.selectByPrimaryKey(detailid);
        detail.setDeleteStatus(1);
        detailMapper.updateOrderDetail(detail);
        //更新原订单总金额
        BigDecimal detailTotal = of.getDetailTotal();
        of.setTotal(of.getTotal().subtract(detailTotal));
        orderMapper.updateOrder(of);
       
        String oldOrderNo = of.getOrderNo();
        String oldOrderId = of.getId();
        //生成新的取消订单
        String newOrderid = UUIDUtil.getUUID();
        of.setId(newOrderid);
        of.setOrderStatus(0);
        String newOrderNo = CommUtil.formatTime("yyyyMMddHHmmss", new Date())+of.getOrderType()+CommUtil.randomInt(4);
        of.setOrderNo(newOrderNo);
        of.setTotal(detailTotal);
        orderMapper.addOrderForm(of);
        //生成订单明细
        detail.setDetailid(UUIDUtil.getUUID());
        detail.setOrderid(newOrderid);
        detail.setDeleteStatus(0);
        detailMapper.addOrderDetail(detail);
        //更新库存
        this.addOrgSkuQty(detail.getQty(), CommUtil.null2Int(detail.getOrgSkuid()));
        
        TmThreadPool.saveOrderLog(logService, oldOrderId, user.getUserid(),
                user.getTrueName(), "用户【"+user.getTrueName()+"】取消商品,取消的商品在订单【"+newOrderNo+"】中");
        TmThreadPool.saveOrderLog(logService, of.getId(), user.getUserid(),
                user.getTrueName(), "用户【"+user.getTrueName()+"】取消的商品,来自订单【"+oldOrderNo+"】");
        
        return 0;
        
    }

    
    public OrderForm getOrderFormByDetailid(String detailid) {
        return orderMapper.getOrderFormByDetailid(detailid);
    }

    
    @Transactional
    public void receiveGoodsForBuyer(List<Map> goodsList, UserInfo user,String orderid) {
        for(Map<String,Object> map:goodsList){
            String detailid = CommUtil.null2String(map.get("detailid"));
            OrderDetail detail = detailMapper.selectByPrimaryKey(detailid);
            BigDecimal realQty = BigDecimal.valueOf(CommUtil.null2Double(map.get("receiveQty")));
            //detail.setDetailid(detailid);
            detail.setRealQty(realQty);
            detail.setDiffQty(realQty.subtract(detail.getQtyBasic()));
            //detail.setDiffStatus(OrderStatusConst.DIFF_STATUS.WAIT_DELIVER_CONFIRM_DIFF.ordinal());
            detail.setDiffStatus(OrderStatusConst.DIFF_STATUS.WAIT_SELLER_CONFIRM_DIF.ordinal());
            detailMapper.updateOrderDetail(detail); 
        }
        OrderForm of = orderMapper.selectByPrimaryKey(orderid);
        of.setOrderStatus(OrderStatusConst.CONFIRM_DIFF);
        orderMapper.updateOrder(of);
        
        TmThreadPool.saveOrderLog(logService, orderid, user.getUserid(),
                user.getTrueName(), "用户【"+user.getTrueName()+"】收货，提交差异确认");
    }

    
    @Transactional
    public void confirmOrderForSeller(OrderForm of, Integer status,
            String reason,UserInfo user) {
        String content=null;
        if(status==1){
            of.setOrderStatus(OrderStatusConst.HAVE_CONFIRM);
            of.setSortingNo(CommUtil.formatTime("yyyyMMddHHmmss", new Date())+CommUtil.randomInt(4));
            orderMapper.updateOrder(of);
            TmThreadPool.saveOrderLog(logService, of.getId(), user.getUserid(),
                    user.getTrueName(), "用户【"+user.getTrueName()+"】确认订单");
            content = "订单号为【"+of.getOrderNo()+"】的订单，商家【"+user.getOrgName()+"】已经确认订单";
        }else{
            of.setOrderStatus(OrderStatusConst.SELLER_CANCEL);
            orderMapper.updateOrder(of);
            //更新库存
            List<OrderDetail> detailList = detailMapper.getDetailQtyByOrderid(of.getId());
            for(OrderDetail detail:detailList){
                this.addOrgSkuQty(detail.getQty(), CommUtil.null2Int(detail.getOrgSkuid()));
            }
            String logStr = "卖家用户【"+user.getTrueName()+"】拒绝订单";
            content = "订单号为【"+of.getOrderNo()+"】的订单，商家【"+user.getOrgName()+"】拒绝订单";
            if(StringUtils.isNotBlank(reason)){
                logStr+="，理由：【"+reason+"】";
                content+="，理由：【"+reason+"】";
            }
            TmThreadPool.saveOrderLog(logService, of.getId(), user.getUserid(),
                    user.getTrueName(), logStr);
        }       
        //系统消息
        Message msg = new Message();
        msg.setUserid(of.getUserid());
        msg.setContent(content);
        msgMapper.insertMessage(msg);
    }

    
    @Transactional
    public void cancelOrderForSeller(OrderForm of, UserInfo user,String reason) {
        of.setOrderStatus(OrderStatusConst.SELLER_CANCEL);
        orderMapper.updateOrder(of);
        //更新库存
        List<OrderDetail> detailList = detailMapper.getDetailQtyByOrderid(of.getId());
        for(OrderDetail detail:detailList){
            this.addOrgSkuQty(detail.getQty(), CommUtil.null2Int(detail.getOrgSkuid()));
        }
        String content = "";
        if(StringUtils.isNotBlank(reason)){
            content = ",理由：【"+reason+"】";
        }
        TmThreadPool.saveOrderLog(logService, of.getId(), user.getUserid(),
                user.getTrueName(), "卖家用户【"+user.getTrueName()+"】取消订单"+content);
        //系统消息
        Message msg = new Message();
        msg.setUserid(of.getUserid());
        msg.setContent("订单号为【"+of.getOrderNo()+"】的订单，商家【"+user.getOrgName()+"】取消订单"+content);
        msgMapper.insertMessage(msg);
     
        
    }

    
    @Transactional
    public void payForSeller(List<OrderForm> orderList) {
//        String[] ids = orderid.split(",");
//        for (String id : ids) {
//            OrderForm of = orderMapper.selectByPrimaryKey(id);
//            if (of == null
//                    || of.getOrderStatus() != OrderStatusConst.HAVE_RECEIVE
//                    || of.getPayStatus() == OrderStatusConst.PAY_STATUS.HAVE_PAY
//                            .ordinal()) {
//                continue;
//            }
//            of.setPayStatus(OrderStatusConst.PAY_STATUS.HAVE_PAY.ordinal());
//            of.setPayDate(new Date());
//            orderMapper.updateOrder(of);
//        }
        for(OrderForm of:orderList){
          of.setPayStatus(OrderStatusConst.PAY_STATUS.HAVE_PAY.ordinal());
          of.setPayDate(new Date());
          orderMapper.updateOrder(of);
        }
        
    }

    
    @Transactional
    public int deliverForSeller(OrderLogistics deliver,UserInfo user) {
        OrderForm order = orderMapper.selectByPrimaryKey(deliver.getOrderid());
        if(order==null||order.getOrderStatus()!=OrderStatusConst.NOT_DELIVERY){
            return -1;
        }
        deliver.setId(UUIDUtil.getUUID());
        deliver.setDistributeDate(CommUtil.formatLongDate(new Date()));
        logisticsMapper.insert(deliver);
        //更新为待收货
        order.setOrderStatus(OrderStatusConst.WAIT_RECEVIE);
        order.setDeliveryDate(new Date());
        orderMapper.updateOrder(order);
        TmThreadPool.saveOrderLog(logService, order.getId(), user.getUserid(),
                user.getTrueName(), "卖家用户【"+user.getTrueName()+"】发货");
        //系统消息
        Message msg = new Message();
        msg.setUserid(order.getUserid());
        msg.setContent("订单号为【"+order.getOrderNo()+"】的订单，商家【"+user.getOrgName()+"】已经完成发货");
        msgMapper.insertMessage(msg);
        return 0;
    }

    
    @Transactional
    public int confirmDiffForSeller(Map<String, Object> map, UserInfo user) {
       String detailid = CommUtil.null2String(map.get("detailid"));
       OrderDetail detail = detailMapper.selectByPrimaryKey(detailid);
       if(detail.getDiffStatus()!=OrderStatusConst.DIFF_STATUS.WAIT_SELLER_CONFIRM_DIF.ordinal()){
           return -1;
       }
       //差异状态
       Integer status = CommUtil.null2Int(map.get("status"));
       if(status==1){
           detail.setDiffStatus(OrderStatusConst.DIFF_STATUS.DIFF_END.ordinal());
       }else{
           String reason = CommUtil.null2String(map.get("reason"));
           detail.setDiffStatus(OrderStatusConst.DIFF_STATUS.HAVE_DIFF.ordinal());
           detail.setDiffDesc(reason);
       }
       detailMapper.updateOrderDetail(detail);
       
       String orderid = CommUtil.null2String(map.get("orderid"));
       int num = detailMapper.getDiffDetailNumByOrderid(orderid);
       if(num == 0){
           OrderForm of = orderMapper.selectByPrimaryKey(orderid);
           of.setOrderStatus(OrderStatusConst.HAVE_RECEIVE);
           of.setReceiveDate(new Date());
           //更新收货总额
           OrderForm receiveOrder =orderMapper.getReceiveTotalByOrderid(orderid);
           of.setReceiveTotal(receiveOrder.getReceiveTotal());
           orderMapper.updateOrder(of);
           TmThreadPool.saveOrderLog(logService, of.getId(), user.getUserid(),
                   user.getTrueName(), "卖家用户【"+user.getTrueName()+"】确认差异完成，收货完成");
       }
       return 0;        
    }

    
    @Transactional
    public int confirmDiffForDistributor(Map<String, Object> map, UserInfo user) {
        String detailid = CommUtil.null2String(map.get("detailid"));
        OrderDetail detail = detailMapper.selectByPrimaryKey(detailid);
        if(detail.getDiffStatus()!=OrderStatusConst.DIFF_STATUS.WAIT_DELIVER_CONFIRM_DIFF.ordinal()){
            return -1;
        }
        //差异状态
        Integer status = CommUtil.null2Int(map.get("status"));
        if(status==1){
            detail.setDiffStatus(OrderStatusConst.DIFF_STATUS.WAIT_SELLER_CONFIRM_DIF.ordinal());
        }else{
            String reason = CommUtil.null2String(map.get("reason"));
            detail.setDiffStatus(OrderStatusConst.DIFF_STATUS.HAVE_DIFF.ordinal());
            detail.setDiffDesc(reason);
        }
        detailMapper.updateOrderDetail(detail);
        return 0;
    }

    
    public List<OrderDetail> getDiffDetailByOrderid(String orderid) {
       return detailMapper.getDetailListByOrderid(orderid);
    }

    
    public PageInfo<OrderForm> getOrderListForSeller(Map<String, Object> map,
            int page, int rows) {
        PageHelper.startPage(page,rows);
        List<OrderForm> orderList = orderMapper.getOrderListForSeller(map);
        PageInfo<OrderForm> info = new PageInfo<OrderForm>(orderList);
        return info;
    }

    
    public List<OrderDetail> getOrderDetailForSeller(String orderid) {        
        return detailMapper.getDetailListByOrderid(orderid);
    }

    
    @Transactional
    public void saveOrderForSeller(String addressInfo, String deliverInfo) {
        if(StringUtils.isNotBlank(addressInfo)){
            Map<String,Object> addressMap = JSONObject.parseObject(addressInfo);
            orderMapper.updateReceiver(addressMap);
        }        
        Map<String,Object> deliverMap = JSONObject.parseObject(deliverInfo);       
        logisticsMapper.updateByMap(deliverMap);     
    }

    
    @Transactional
    public void addGoodsTrace(GoodsTrace trace) {
       GoodsTrace newTrace = traceMapper.getTraceByDetailid(trace.getOrderDetailid());
       String traceid = null;
       if(newTrace!=null){
           traceid = newTrace.getId();
           trace.setId(traceid);
           traceMapper.updateTrace(trace);           
           tracePicMapper.deletePicByTraceid(traceid);
       }else{
           newTrace = traceMapper.getTraceInfoByDetailid(trace.getOrderDetailid());
           traceid = UUIDUtil.getUUID();
           trace.setId(traceid);
           trace.setBatchNo(CommUtil.formatTime("yyyyMMddHHmmss", new Date())+newTrace.getSkuCode()+CommUtil.randomInt(4));
           trace.setAddDate(new Date());
           trace.setGoodsid(newTrace.getGoodsid());
           trace.setGoodsSkuid(newTrace.getGoodsSkuid());
           trace.setOrderid(newTrace.getOrderid());
           trace.setOrgid(newTrace.getOrgid());
           trace.setTraceType(0);
           traceMapper.addTrace(trace);              
       }
       
       List<GoodsTracePic> picList = trace.getPicList();
       for(GoodsTracePic pic:picList){
           pic.setId(UUIDUtil.getUUID());
           pic.setTraceid(traceid);
           tracePicMapper.addTracePic(pic);
       }
       //更新订单明细表中状态
       OrderDetail detail = detailMapper.selectByPrimaryKey(trace.getOrderDetailid());
       detail.setTraceStatus(1);
       detailMapper.updateOrderDetail(detail);       
        
    }

    
    public GoodsTrace getGoodsTraceByOrderDetailid(String orderDetailid) {
        GoodsTrace trace = traceMapper.getTraceByDetailid(orderDetailid);
        if(trace!=null){
            List<GoodsTracePic> picList = tracePicMapper.getPicByTraceid(trace.getId());
            trace.setPicList(picList);
        }
        return trace;
    }

    
    @Transactional
    public int traceComplete(OrderForm o,UserInfo user) {
        int num = orderMapper.getNotTraceNumByOrderid(o.getId());
        if(num == 0){
            OrderForm of = orderMapper.selectByPrimaryKey(o.getId());
            if(of==null||of.getOrderStatus()<OrderStatusConst.HAVE_CONFIRM||of.getOrderStatus()>=OrderStatusConst.WAIT_RECEVIE){
                return -2;
            }
            //更新出库数量
            for(OrderDetail detail:o.getOrderDetails()){
                if(detail.getOutQty().compareTo(BigDecimal.ZERO)<=0){
                    return -3;
                }
                detailMapper.updateOrderDetail(detail);
            }
            
            of.setOrderStatus(OrderStatusConst.NOT_DELIVERY);
            
            //更新出库金额
            OrderForm outOrder = orderMapper.getOutTotalAndCostTotalByOrderid(o.getId());
            of.setOutTotal(outOrder.getOutTotal());
            of.setCostTotal(outOrder.getCostTotal());
            orderMapper.updateOrder(of);
            
            TmThreadPool.saveOrderLog(logService, of.getId(), user.getUserid(),
                    user.getTrueName(), "卖家用户【"+user.getTrueName()+"】完成分拣");
            
            return 0;
        }
        return -1;
        
    }

    
    @Transactional
    public int updateTraceCount(GoodsTrace trace,UserInfo user) {
        OrderDetail detail = detailMapper.selectByPrimaryKey(trace.getOrderDetailid());
        OrderForm of = orderMapper.selectByPrimaryKey(detail.getOrderid());
        if(of==null||of.getOrderStatus()>=OrderStatusConst.WAIT_RECEVIE||of.getOrderStatus()<OrderStatusConst.HAVE_CONFIRM){
            return -1;
        }
        detail.setOutQty(trace.getSortQty());
        detailMapper.updateOrderDetail(detail);
        GoodsTrace newTrace = traceMapper.getTraceByDetailid(trace.getOrderDetailid());
        if(newTrace!=null){
            newTrace.setSortQty(trace.getSortQty());
            traceMapper.updateTrace(newTrace);
            
            int num = orderMapper.getNotTraceNumByOrderid(detail.getOrderid());
            if(num==0){
               
                of.setOrderStatus(OrderStatusConst.NOT_DELIVERY);
                //更新出库金额
                OrderForm outOrder = orderMapper.getOutTotalAndCostTotalByOrderid(detail.getOrderid());
                of.setOutTotal(outOrder.getOutTotal());
                of.setCostTotal(outOrder.getCostTotal());
                orderMapper.updateOrder(of);
                
                TmThreadPool.saveOrderLog(logService, of.getId(), user.getUserid(),
                        user.getTrueName(), "卖家用户【"+user.getTrueName()+"】完成分拣");               
            }            
            return 0;
        }       
        return -1;
    }

    
    public OrderForm getOrderSummary(String userid) {       
        OrderForm of = orderMapper.getOrderCountByUser(userid);
        OrderForm returnOf = orderMapper.getOrderRetrunTotal(userid);
        of.setTotal(of.getTotal().subtract(returnOf.getReturnTotal()).setScale(2, BigDecimal.ROUND_HALF_UP));
        return of;
        
    }

    
    public PageInfo<OrderDetail> getTraceByGoods(Map<String, Object> map,int page,int rows) {
        PageHelper.startPage(page,rows);
        List<OrderDetail> traceGoodsList = detailMapper.getTraceByGoods(map);
        for(OrderDetail detail:traceGoodsList){
            map.put("skuid", detail.getOrgSkuid());
            List<OrderDetail> detailList = detailMapper.getTraceGoodsDetail(map);
            detail.setGoodsTraceList(detailList);
        }
        PageInfo<OrderDetail> info = new PageInfo<OrderDetail>(traceGoodsList);        
        return info;
    }

    
    public PageInfo<OrderForm> getReceiveListForDistributor(
            Map<String, Object> map,int page,int rows) {
        PageHelper.startPage(page,rows);
        List<OrderForm> orderList = orderMapper.getReceiveListForDistributor(map);
        PageInfo<OrderForm> info = new PageInfo<OrderForm>(orderList);
        for(OrderForm o:info.getList()){
            List<OrderDetail> detailList = detailMapper.getDetailListByOrderid(o.getId());
            o.setOrderDetails(detailList);
        }
        return info;
    }


    
    public OrderDetail getOrderDetailInfoByTraceId(String traceId){
    	return detailMapper.getOrderDetailByTraceid(traceId);
    }

}
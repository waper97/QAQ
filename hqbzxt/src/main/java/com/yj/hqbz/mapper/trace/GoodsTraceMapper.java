package com.yj.hqbz.mapper.trace;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.trace.GoodsTrace;

public interface GoodsTraceMapper {
    GoodsTrace selectByPrimaryKey(String id);
    
    int addTrace(GoodsTrace trace);
    
    GoodsTrace getTraceBySporadicid(String indexid);
    
    int updateTrace(GoodsTrace trace);
    
    GoodsTrace getTraceInfoByDetailid(String orderDetailid);
    GoodsTrace getTraceByDetailid(String orderDetailid);

    
    //===================溯源统计===========================
    List<Map<String,Object>> getGoodsBySchool(Map<String,Object> param);
    
    List<Map<String,Object>> getMenuBySchool(Map<String,Object> param);
    
    List<Map<String,Object>> getStoreBySchool(Map<String,Object> param);
    
    List<Map<String,Object>> getTraceByPurchase(String traceid);
    
    Map<String,Object> getTraceByDeliver(String traceid);
    
    Map<String,Object> getTraceByReceive(String traceid);
    
    Map<String,Object> getTraceByInStock(String traceid);
    
    List<Map<String,Object>> getTraceByOutStock(Map<String,Object> param);
    
    List<Map<String,Object>> getTraceByUse(Map<String,Object> param);
    
    List<Map<String,Object>> getSchoolStock(Map<String,Object> param);
    
    List<Map<String,Object>> getSchoolByExpireFood(Map<String,Object> param);
    
    List<Map<String,Object>> getExpireFood(Map<String,Object> param);
    
    List<Map<String,Object>> getMaterial(Map<String,Object> param);
    
    Map<String,Object> getStoreInfo(Integer orgid);
    
    int getCountByStore(Map<String,Object> param);
    
    List<Map<String,Object>> getOrderInfoByStore(Map<String,Object> param);
    
    List<Map<String,Object>> getStoreRanking();
    

}
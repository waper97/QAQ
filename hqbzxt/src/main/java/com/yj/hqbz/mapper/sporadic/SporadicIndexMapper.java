package com.yj.hqbz.mapper.sporadic;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.sporadic.SporadicIndex;

public interface SporadicIndexMapper {
    SporadicIndex selectByPrimaryKey(String id);
    
    List<Map<String,Object>> getInStockBySporadic(Map<String,Object> param);
    
    int addIndex(SporadicIndex index);
    
    int updateIndex(SporadicIndex index);
    
    int deleteSporadic(SporadicIndex index);
    
    
    SporadicIndex getSporadicIndexByTraceId(String traceid);
}
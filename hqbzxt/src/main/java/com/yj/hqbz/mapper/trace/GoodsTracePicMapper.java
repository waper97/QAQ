package com.yj.hqbz.mapper.trace;

import java.util.List;

import com.yj.hqbz.model.trace.GoodsTracePic;

public interface GoodsTracePicMapper {
    GoodsTracePic selectByPrimaryKey(String id);
    
    List<GoodsTracePic> getPicByTraceid(String traceid);
    
    int addTracePic(GoodsTracePic pic);
    
    int deletePicByTraceid(String traceid);
    
    List<GoodsTracePic> getGoodsPicByTraceid(String traceid);
}
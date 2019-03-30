package com.yj.hqbz.mapper.inventory;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.inventory.BVoucherDetail;
import com.yj.hqbz.model.inventory.BVoucherIndex;

public interface BVoucherDetailMapper {
    BVoucherDetail selectByPrimaryKey(String id);
    
    int addBVoucherDetailByIn(BVoucherDetail detail);
    
    int addBVoucherDetailByOut(BVoucherDetail detail);
    
    List<Map<String,Object>> getOutInDetail(String stockid);
}
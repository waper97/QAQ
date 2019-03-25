package com.yj.hqbz.mapper.inventory;

import com.yj.hqbz.model.inventory.BVoucherDetail;
import com.yj.hqbz.model.inventory.BVoucherIndex;

public interface BVoucherDetailMapper {
    BVoucherDetail selectByPrimaryKey(String id);
    
    int addBVoucherDetail(BVoucherDetail detail);
}
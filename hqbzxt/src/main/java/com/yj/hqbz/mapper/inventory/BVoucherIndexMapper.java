package com.yj.hqbz.mapper.inventory;

import com.yj.hqbz.model.inventory.BVoucherIndex;

public interface BVoucherIndexMapper {
    BVoucherIndex selectByPrimaryKey(String id);
    
    int addBVoucherIndex(BVoucherIndex index);
}
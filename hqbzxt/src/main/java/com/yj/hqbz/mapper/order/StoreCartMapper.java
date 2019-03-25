package com.yj.hqbz.mapper.order;

import java.util.Map;

import com.yj.hqbz.model.order.StoreCart;

public interface StoreCartMapper {
    StoreCart selectByPrimaryKey(Integer id);
    //添加商店购物车
    int addStoreCart(StoreCart scCart);
    //删除商店购物车
    void deleteStoreCart(String id);
    //查询当前用户该机构是否有未结算的商品
    StoreCart getStoreCartBySkuidAndUserid(Map<String,Object> map);
    
}
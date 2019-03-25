package com.yj.hqbz.services.order;

import java.util.Map;

import com.yj.hqbz.model.order.StoreCart;

public interface StoreCartService {

    //添加购物车
    void addStoreCart(StoreCart sc);
    //查询当前用户当前店铺购物车
    StoreCart getCartByStoreidAndUserid(Map<String,Object> map);
    //购物车列表
    
    
}

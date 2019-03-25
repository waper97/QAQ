package com.yj.hqbz.mapper.order;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.order.GoodsCart;

public interface GoodsCartMapper {
    GoodsCart selectByPrimaryKey(String id);
    //检查购物车中是否有重复
    GoodsCart checkGoodsCartExist(Map<String,Object> map);
    //更新购物车
    void update(GoodsCart gc);
    //添加购物车
    void addGoodsCart(GoodsCart gc);
    //删除购物车
    void deleteGoodsCart(String id);
    //根据scid获取goodscart数量
    int getLastGoodsCart(String scid);
    //根据用户获取购物车列表
    List<Map<String,Object>> getGoodsCartListByUserid(String userid);
    //根据用户购物车明细ID获得购物车详情
    List<Map<String,Object>> getGoodsCartListByUseridAndGcid(Map<String,Object> map);
    
}
package com.yj.hqbz.services.order;



import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.order.GoodsCart;

public interface GoodsCartService {

    //根据购物车ID查询购物车
    GoodsCart getGoodsCartById(String id);
    //添加购物车
    String addGoodsCart(GoodsCart gc,String userid,Integer orgid);
    //删除购物车
    void deleteGoodsCart(String id);
    //更新购物车数量
    void updateGoodsCartNum(GoodsCart gc);
    //更新购物车销售规格
    void updateGoodsCartSku(GoodsCart gc);
    //获取购物车列表根据userid
    List<Map<String,Object>> getGoodsCartByUser(String userid);
    //根据用户和GCid获取购物车详情
    List<Map<String,Object>> getGoodsCartByUserAndGcid(String userid,String gcid);
   
}

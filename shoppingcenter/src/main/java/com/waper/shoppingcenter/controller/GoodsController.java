package com.waper.shoppingcenter.controller;

import com.waper.shoppingcenter.common.UUIDUtil;
import com.waper.shoppingcenter.dao.GoodsDao;
import com.waper.shoppingcenter.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsController {
    @Autowired
    GoodsDao goodsDao;

    /**
     * 添加商品
     * @param goods
     * @return
     */
    @RequestMapping("shop/goods/insertGoods")
    public String insertGoods(Goods goods){
        if (goods.getName() == null || goods.getType() == null) {
            throw new RuntimeException("商品参数不能为空");
        }
        goods.setId(UUIDUtil.getUUID());
        goods.setStatus(0);
        goodsDao.save(goods);
        return "添加成功";
    }
}

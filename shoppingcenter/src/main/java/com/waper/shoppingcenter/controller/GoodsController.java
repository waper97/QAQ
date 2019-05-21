package com.waper.shoppingcenter.controller;

import com.waper.shoppingcenter.common.UUIDUtil;
import com.waper.shoppingcenter.dao.GoodsDao;
import com.waper.shoppingcenter.model.Goods;
import com.waper.shoppingcenter.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.List;

@RestController
public class GoodsController {
    @Autowired
    GoodsDao goodsDao;
    @Autowired
    GoodsService goodsService;
    /**
     * 添加商品
     * @param goods
     * @return
     */
    @RequestMapping("shop/goods/insertGoods")
    public Object insertGoods(Goods goods){
        if(goods.getId() == null){
            if (goods.getName() == null || goods.getType() == null) {
                throw new RuntimeException("商品参数不能为空");
            }
            goods.setId(UUIDUtil.getUUID());
            goods.setStatus(0);
            goodsDao.save(goods);
        }else{
            goodsDao.save(goods);
        }

        return new BaseResponse(true,"操作成功");
    }

    /**
     * 商品列表
     * @return
     */
    @RequestMapping("shop/goods/goodList")
    public Object goodList(){
        Page<Goods> goodsList = goodsService.getGoodsList(1,10);
        return new BaseResponse(true,goodsList);
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @RequestMapping("shop/goods/deleteGoodsById")
    public Object deleteGoodsById( String id){
            goodsDao.deleteById(id);
        return new BaseResponse(true,"成功");
    }
//    @RequestMapping("shop/goods/updateGoodById")
//    public Object updateGoodById()
//    }
}

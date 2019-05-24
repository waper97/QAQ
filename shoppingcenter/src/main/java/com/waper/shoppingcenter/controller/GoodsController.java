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
     * TODO：太耦合了要修改
     * @return
     */
    @RequestMapping("shop/goods/insertOrUpdateGoods")
    public Object insertOrUpdateGoods(Goods goods){
        if(goods.getId() == null && "".equals(goods.getId())){
            goods.setId(UUIDUtil.getUUID());
            goods.setStatus(0);
            goodsDao.save(goods);
        }else{
            Goods goodsFind = goodsDao.findById(goods.getId()).get();
            goodsFind.setName(goods.getName());
            goodsFind.setStatus(goods.getStatus());
            goodsFind.setPircture(goods.getPircture());
            goodsFind.setType(goods.getType());
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

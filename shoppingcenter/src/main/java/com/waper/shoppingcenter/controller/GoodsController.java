package com.waper.shoppingcenter.controller;

import com.github.pagehelper.PageInfo;
import com.waper.shoppingcenter.common.UUIDUtil;
import com.waper.shoppingcenter.model.Goods;
import com.waper.shoppingcenter.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GoodsController {

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
        if(goods.getId() == null || "".equals(goods.getId())){
            goods.setId(UUIDUtil.getUUID());
            goods.setStatus(0);
            goodsService.insertGoods(goods);
        }else{
            Goods goodsFind = goodsService.selectGoodsById(goods.getId());
            if (goodsFind == null) {
                return new BaseResponse("数据不存在!");
            }
            goodsService.updateById(goods);
        }

        return new BaseResponse(true,"操作成功");
    }


    /**
     * 商品列表
     * @return
     */
    @RequestMapping("shop/goods/goodList")
    public Object goodList(Integer page, Integer pageSize){
        if(StringUtils.isEmpty(page)) {
            page = 1;
        }
        if(StringUtils.isEmpty(pageSize)){
            pageSize = 10;
        }
        Map<String,Object> paramMap = new HashMap<>();
        PageInfo<Goods> goodsList = goodsService.getGoodsList(page, pageSize, paramMap);
        return new BaseResponse(true,goodsList);
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @RequestMapping("shop/goods/deleteGoodsById")
    public Object deleteGoodsById( String id){
            goodsService.deleteGoodsByPrimaryKey(id);
        return new BaseResponse(true,"删除成功");
    }

    @RequestMapping("shop/goods/updateGoodById")
    public Object updateGoodById(@RequestBody Goods goods){
        int result = goodsService.updateById(goods);
        return new BaseResponse(true,"修改成功");
    }
}

package com.waper.shoppingcenter.service;

import com.github.pagehelper.PageInfo;
import com.waper.shoppingcenter.model.Goods;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * create by  on 2019/5/21
 * *
 **/

public interface GoodsService {
    /**
     * 获取列表
     * @param pageNum
     * @param pageLimit
     * @param paramMap
     * @return
     */
    PageInfo<Goods> getGoodsList(Integer pageNum, Integer pageLimit, Map<String,Object> paramMap);

    int deleteGoodsByPrimaryKey(String id);
    /**
     * 添加商品
     * @param goods
     * @return
     */
    int insertGoods(Goods goods);
    /**
     * 查看详情
     * @param goodsId
     * @return
     */
    Goods selectGoodsById(String goodsId);

    /**
     * 修改商品
     * @param goods
     * @return
     */
    int updateById(Goods goods);
}

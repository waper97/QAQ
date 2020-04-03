package com.waper.shoppingcenter.service;

import com.github.pagehelper.PageInfo;
import com.waper.shoppingcenter.model.Goods;
import com.waper.shoppingcenter.model.GoodsType;

import java.util.Map;

public interface GoodsTypeService {

    /**
     * 获取列表
     * @param pageNum
     * @param pageLimit
     * @param paramMap
     * @return
     */
    PageInfo<GoodsType> listGoodsTypeList( Map<String,Object> paramMap, int pageNum, int pageLimit);

    /**
     * 删除商品类型
     * @param id
     * @return
     */
    int delGoodsType(String id);
    /**
     * 添加商品
     * @param goodsType
     * @return
     */
    int insertGoodsType(GoodsType goodsType);
    /**
     * 查看详情
     * @param goodsId
     * @return
     */
    GoodsType getGoodsTypeById(String goodsId);

    /**
     * 修改商品类型
     * @param goodsType
     * @return
     */
    int updateGoodsType(GoodsType goodsType);
}

package com.waper.shoppingcenter.dao.goodstype;

import com.waper.shoppingcenter.model.GoodsType;

import java.util.List;
import java.util.Map;

/**
 * create by  on 2019/5/21
 * *
 **/
public interface GoodsTypeMapper  {
    /**
     * 获取商品类型
     * @param paramMap
     * @return
     */
    List<GoodsType> listGoodsTypes(Map<String,Object> paramMap);

    GoodsType getGoodsType(String id);


    int delGoodsType(String id);
    /**
     * 添加商品
     * @param goodsType
     * @return
     */
    int insertGoodsType(GoodsType goodsType);


    /**
     * 查看详情
     * @param goodsTypeId
     * @return
     */
    GoodsType getGoodsTypeById(String goodsTypeId);

    /**
     * 修改商品类型
     * @param goodsType
     * @return
     */
    int updateGoodsType(GoodsType goodsType);
}

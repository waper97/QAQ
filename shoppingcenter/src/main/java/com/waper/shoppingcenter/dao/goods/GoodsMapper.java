package com.waper.shoppingcenter.dao.goods;

import com.waper.shoppingcenter.model.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {

    List<Goods> listGoods(Map<String,Object> paramMap);

    int deleteByPrimaryKey(String id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}

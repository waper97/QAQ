package com.yj.hqbz.mapper.goods;

import java.util.Map;

import com.yj.hqbz.model.goods.GoodsPic;

public interface GoodsPicMapper {
    GoodsPic selectByPrimaryKey(Integer id);

	void addGoodsPicInfo(GoodsPic goodsPicInfo);

	void updateGoodsPicInfo(GoodsPic goodsPicInfo);

	void deletePic(Map<String, Object> param);

	GoodsPic getGoodsPicByGoodsidAndSkuid(Map<String, Object> param);
}
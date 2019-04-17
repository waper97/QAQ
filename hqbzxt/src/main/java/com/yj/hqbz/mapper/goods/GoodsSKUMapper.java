package com.yj.hqbz.mapper.goods;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.goods.GoodsSKU;

public interface GoodsSKUMapper {
    GoodsSKU selectByPrimaryKey(String skuid);

	void addGoodsSKU(GoodsSKU goodsSKU);

	void updateGoodsSKU(GoodsSKU goodsSKU);

	List<GoodsSKU> getGoodsSKUByGoodsId(String goodsId);

	void updateGoodsSKUStaus(GoodsSKU goodsSKU);

	void deleteGoodsSku(GoodsSKU goodsSKU);

	String getGoodsSpecByGoodsId(String goodsid);
	
	//===================================商品导入===============================
	GoodsSKU getSkuByNameAndGoodsid(Map<String,Object> map);
}
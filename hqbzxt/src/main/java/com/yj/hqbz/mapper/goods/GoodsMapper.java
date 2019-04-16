package com.yj.hqbz.mapper.goods;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.goods.Goods;

public interface GoodsMapper {
    Goods selectByPrimaryKey(String goodsid);
    
    Goods selectGoodsInfoBySkuid(String skuid);
    
	List<Goods> getGoodsByGoodsType(Integer typeId);
	
	List<Goods> getCustomerCanBuyGoodsListByParam(Map<String,Object> param);
	
	
	int getGoodsCountByGoodsType(Integer typeId);

	void addGoods(Goods goods);

	void updateGoods(Goods goods);

	void updateGoodsStatus(Goods goods);

	List<Goods> getGoodsListByParam(Map<String, Object> param);

	Goods getGoodsByGoodsidAndOrgid(Map<String, Object> param);
	
	//=======================================商品导入=================================
	/**根据商品名称获取商品*/
	Goods getGoodsByName(String goodsName);

}
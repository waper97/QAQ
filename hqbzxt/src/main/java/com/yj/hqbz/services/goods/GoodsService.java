/**   
* 
*/
package com.yj.hqbz.services.goods;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yj.common.model.BaseRes;
import com.yj.hqbz.model.goods.Goods;
import com.yj.hqbz.model.goods.GoodsSaleInfo;
import com.yj.hqbz.model.user.UserInfo;

/**   
 * @Title: GoodsService.java
 * @Package com.yj.hqbz.services.impl.goods 
 * @Description: TODO
 * @author xx   
 * @date 2019-2-27 
 */

public interface GoodsService {
	List<Goods> getGoodsByGoodsType(Integer typeId);
	
	int getGoodsCountByGoodsType(Integer typeId);

	void addGoods(Goods goods);

	void updateGoods(Goods goods);

	void updateGoodsStatus(Goods goods);

	void deleteGoods(Goods goods);
	
	Goods selectByPrimaryKey(String goodsid);

	PageInfo<Goods> getGoodsListByParam(Map<String, Object> param,int page,int rows);
	
	//根据参数和客户ID获取客户可购买的商品  (徐霞)
	PageInfo<Goods> getCustomerCanBuyGoodsListByParam(Map<String,Object> param,int page,int rows);

	//批量操作商品启用、停用状态
	void updateManyGoodsStatus(List<Goods> goodsList,Integer status,UserInfo user);
	
	
	
}

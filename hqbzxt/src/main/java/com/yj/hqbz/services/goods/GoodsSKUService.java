/**   
* 
*/
package com.yj.hqbz.services.goods;

import java.util.List;

import com.yj.hqbz.model.goods.GoodsSKU;

/**   
 * @Title: GoodsSKUService.java
 * @Package com.yj.hqbz.services.goods 
 * @Description: TODO
 * @author xx   
 * @date 2019-3-1 
 */
public interface GoodsSKUService {

	void addGoodsSKU(GoodsSKU goodsSKU);

	void updateGoodsSKU(GoodsSKU goodsSKU);

	void updateGoodsSKUStatus(GoodsSKU goodsSKU);

	List<GoodsSKU> getGoodsSKUByGoodsId(String goodsid);
	
	GoodsSKU selectByPrimaryKey(String skuid);

	void deleteGoodsSku(GoodsSKU goodsSKU);

	String getGoodsSpecByGoodsId(String goodsid);

}
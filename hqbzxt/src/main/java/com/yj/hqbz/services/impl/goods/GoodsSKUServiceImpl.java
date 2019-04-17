/**   
* 
*/
package com.yj.hqbz.services.impl.goods;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yj.hqbz.mapper.goods.GoodsSKUMapper;
import com.yj.hqbz.model.goods.GoodsSKU;
import com.yj.hqbz.services.goods.GoodsSKUService;

/**   
 * @Title: GoodsSKUServiceImpl.java
 * @Package com.yj.hqbz.services.impl.goods 
 * @Description: TODO
 * @author xx   
 * @date 2019-3-1 
 */
@Service
public class GoodsSKUServiceImpl implements GoodsSKUService {
	@Resource
	GoodsSKUMapper goodsSKUMapper;

	
	@Transactional
	public void addGoodsSKU(GoodsSKU goodsSKU) {
		goodsSKUMapper.addGoodsSKU(goodsSKU);
	}

	
	@Transactional
	public void updateGoodsSKU(GoodsSKU goodsSKU) {
		goodsSKUMapper.updateGoodsSKU(goodsSKU);
	}

	
	@Transactional
	public void updateGoodsSKUStatus(GoodsSKU goodsSKU) {
		goodsSKUMapper.updateGoodsSKUStaus(goodsSKU);
	}

	
	public List<GoodsSKU> getGoodsSKUByGoodsId(String goodsid) {
		return goodsSKUMapper.getGoodsSKUByGoodsId(goodsid);
	}

	
	public GoodsSKU selectByPrimaryKey(String skuid) {
		return goodsSKUMapper.selectByPrimaryKey(skuid);
	}

	
	public void deleteGoodsSku(GoodsSKU goodsSKU) {
		goodsSKUMapper.deleteGoodsSku(goodsSKU);
	}

	
	public String getGoodsSpecByGoodsId(String goodsid) {
		return goodsSKUMapper.getGoodsSpecByGoodsId(goodsid);
	}
	
	public GoodsSKU getSkuByNameAndGoodsid(Map<String, Object> map) {
		return goodsSKUMapper.getSkuByNameAndGoodsid(map);
	}
}

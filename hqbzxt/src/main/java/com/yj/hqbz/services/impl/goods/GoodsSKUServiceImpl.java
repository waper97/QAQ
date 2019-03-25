/**   
* 
*/
package com.yj.hqbz.services.impl.goods;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.yj.hqbz.mapper.goods.GoodsMapper;
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

	@Override
	@Transactional
	public void addGoodsSKU(GoodsSKU goodsSKU) {
		goodsSKUMapper.addGoodsSKU(goodsSKU);
	}

	@Override
	@Transactional
	public void updateGoodsSKU(GoodsSKU goodsSKU) {
		goodsSKUMapper.updateGoodsSKU(goodsSKU);
	}

	@Override
	@Transactional
	public void updateGoodsSKUStatus(GoodsSKU goodsSKU) {
		goodsSKUMapper.updateGoodsSKUStaus(goodsSKU);
	}

	@Override
	public List<GoodsSKU> getGoodsSKUByGoodsId(String goodsid) {
		return goodsSKUMapper.getGoodsSKUByGoodsId(goodsid);
	}

	@Override
	public GoodsSKU selectByPrimaryKey(String skuid) {
		return goodsSKUMapper.selectByPrimaryKey(skuid);
	}

	@Override
	public void deleteGoodsSku(GoodsSKU goodsSKU) {
		goodsSKUMapper.deleteGoodsSku(goodsSKU);
	}

	@Override
	public String getGoodsSpecByGoodsId(String goodsid) {
		return goodsSKUMapper.getGoodsSpecByGoodsId(goodsid);
	}
}

/**   
* 
*/
package com.yj.hqbz.services.impl.goods;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.yj.hqbz.mapper.goods.GoodsPicMapper;
import com.yj.hqbz.model.goods.GoodsPic;
import com.yj.hqbz.services.goods.GoodsPicInfoService;

/**   
 * @Title: GoodPicInfoServiceImpl.java
 * @Package com.yj.hqbz.services.impl.goods 
 * @Description: TODO
 * @author xx   
 * @date 2019-3-1 
 */
@Service
public class GoodPicInfoServiceImpl implements GoodsPicInfoService {
	@Resource
	GoodsPicMapper goodsPicMapper;

	@Override
	@Transactional
	public void addGoodsPic(GoodsPic goodsPic) {
		goodsPicMapper.addGoodsPicInfo(goodsPic);
	}

	@Override
	public GoodsPic selectByPrimaryKey(Integer id) {
		return goodsPicMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public void updateGoodsPic(GoodsPic goodsPic) {
		goodsPicMapper.updateGoodsPicInfo(goodsPic);
	}


	@Override
	public void deletePic(String goodsid, String skuid) {
		Map<String, Object> param=new HashMap<>();
		param.put("goodsid", goodsid);
		param.put("skuid", skuid);
		goodsPicMapper.deletePic(param);
	}

	@Override
	public GoodsPic getGoodsPicByGoodsId(String goodsid,String skuid) {
		Map<String, Object> param=new HashMap<>();
		param.put("goodsid", goodsid);
		param.put("skuid", skuid);
		return goodsPicMapper.getGoodsPicByGoodsidAndSkuid(param);
	}
	
}

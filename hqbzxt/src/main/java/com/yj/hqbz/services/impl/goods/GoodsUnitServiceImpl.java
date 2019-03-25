/**   
* 
*/
package com.yj.hqbz.services.impl.goods;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yj.hqbz.mapper.goods.GoodsUnitMapper;
import com.yj.hqbz.model.goods.GoodsUnit;
import com.yj.hqbz.services.goods.GoodsUnitService;

/**   
 * @Title: GoodsUnitServiceImpl.java
 * @Package com.yj.hqbz.services.impl.goods 
 * @Description: TODO
 * @author xx   
 * @date 2019-2-28 
 */
@Service
public class GoodsUnitServiceImpl implements GoodsUnitService {
	
	@Resource
	GoodsUnitMapper goodsUnitMapper;
	@Override
	public List<GoodsUnit> getGoodsUnitList() {
		return goodsUnitMapper.getGoodsUnitList();
	}

}

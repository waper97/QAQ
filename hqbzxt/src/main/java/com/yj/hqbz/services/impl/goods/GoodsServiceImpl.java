/**   
* 
*/
package com.yj.hqbz.services.impl.goods;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.goods.GoodsMapper;
import com.yj.hqbz.mapper.goods.GoodsPicMapper;
import com.yj.hqbz.mapper.goods.GoodsSKUMapper;
import com.yj.hqbz.mapper.goods.GoodsTypeMapper;
import com.yj.hqbz.model.goods.Goods;
import com.yj.hqbz.model.goods.GoodsPic;
import com.yj.hqbz.model.goods.GoodsSKU;
import com.yj.hqbz.model.goods.GoodsType;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.goods.GoodsService;
import com.yj.hqbz.util.UUIDUtil;

/**   
 * @Title: GoodsServiceImpl.java
 * @Package com.yj.hqbz.services.impl.goods 
 * @Description: TODO
 * @author xx   
 * @date 2019-2-27 
 */
@Service
public class GoodsServiceImpl implements GoodsService {
	@Resource
	GoodsMapper goodsMapper;
	@Resource
	GoodsSKUMapper goodsSKUMapper;
	@Resource
	GoodsTypeMapper goodsTypeMapper;
	@Resource
	GoodsPicMapper goodsPicMapper;
	
	
	public PageInfo<Goods> getCustomerCanBuyGoodsListByParam(Map<String,Object> param,int page,int rows){
		PageHelper.startPage(page, rows);
		List<Goods> list = goodsMapper.getCustomerCanBuyGoodsListByParam(param);
		PageInfo<Goods> info=new PageInfo<Goods>(list);
		return info;
	}
	
	
	public List<Goods> getGoodsByGoodsType(Integer typeId) {
		return goodsMapper.getGoodsByGoodsType(typeId);
	}
	
	public int getGoodsCountByGoodsType(Integer typeId) {
		return goodsMapper.getGoodsCountByGoodsType(typeId);
	}

	@Transactional
	public void addGoods(Goods goods) {
		goodsMapper.addGoods(goods);
		
		String goodsid = goods.getGoodsid();
		GoodsPic goodsPic=new GoodsPic();
		goodsPic.setPicUrl(goods.getPicUrl());
		goodsPic.setThumbnailUrl(goods.getThumbnailUrl());
		goodsPic.setGoodsid(goodsid);
		goodsPicMapper.addGoodsPicInfo(goodsPic);
		
		//商品sku
		List<GoodsSKU> skuList = goods.getSkuList();
		for (GoodsSKU sku : skuList) {
			String skuid = UUIDUtil.getUUID();
			sku.setSkuid(skuid);
			sku.setGoodsid(goodsid);
			goodsSKUMapper.addGoodsSKU(sku);
			GoodsPic pic=new GoodsPic();
			pic.setPicUrl(sku.getPicUrl());
			pic.setThumbnailUrl(sku.getThumbnailUrl());
			pic.setGoodsid(goodsid);
			pic.setSkuid(skuid);
			goodsPicMapper.addGoodsPicInfo(pic);
			
		}
	}

	@Transactional
	public void updateGoods(Goods goods) {
		goodsMapper.updateGoods(goods);
		
		String goodsid = goods.getGoodsid();
		Map<String , Object> param=new HashMap<>();
		param.put("goodsid", goodsid);
		param.put("skuid", null);
		goodsPicMapper.deletePic(param);
		GoodsPic goodsPic=new GoodsPic();
		goodsPic.setPicUrl(goods.getPicUrl());
		goodsPic.setThumbnailUrl(goods.getThumbnailUrl());
		goodsPic.setGoodsid(goods.getGoodsid());
		goodsPicMapper.addGoodsPicInfo(goodsPic);
		
		//商品sku
		List<GoodsSKU> skuList = goods.getSkuList();
		for (GoodsSKU sku : skuList) {
			if(sku.getSkuid()!=null){
				//为修改
				goodsSKUMapper.updateGoodsSKU(sku);
				
				param.clear();
				param.put("goodsid", goodsid);
				param.put("skuid", sku.getSkuid());
				goodsPicMapper.deletePic(param);
				GoodsPic pic=new GoodsPic();
				pic.setPicUrl(sku.getPicUrl());
				pic.setThumbnailUrl(sku.getThumbnailUrl());
				pic.setGoodsid(goods.getGoodsid());
				pic.setSkuid(sku.getSkuid());
				goodsPicMapper.addGoodsPicInfo(pic);
				
			}else{
				//为新增
				String skuid = UUIDUtil.getUUID();
				sku.setSkuid(skuid);
				sku.setGoodsid(goodsid);
				goodsSKUMapper.addGoodsSKU(sku);
				GoodsPic pic=new GoodsPic();
				pic.setPicUrl(sku.getPicUrl());
				pic.setThumbnailUrl(sku.getThumbnailUrl());
				pic.setGoodsid(goodsid);
				pic.setSkuid(skuid);
				goodsPicMapper.addGoodsPicInfo(pic);
				
			}
			
		}
		
	}

	@Transactional
	public void updateGoodsStatus(Goods goods) {
		goodsMapper.updateGoodsStatus(goods);
		if(goods.getStatus()==1){
			//如果商品停用，则全部子规格停用
			List<GoodsSKU> list=goodsSKUMapper.getGoodsSKUByGoodsId(goods.getGoodsid());
			for (GoodsSKU g : list) {
				g.setStatus(1);
				g.setLasteDitor(goods.getLastOpUser());
				g.setLasteDitDate(new Date());
				goodsSKUMapper.updateGoodsSKUStaus(g);
			}
		}
		//商品启用，子规格状态不变，仍为停用。
	}

	@Transactional
	public void deleteGoods(Goods goods) {
		goodsMapper.updateGoodsStatus(goods);
	}

	public Goods selectByPrimaryKey(String goodsid) {
		return goodsMapper.selectByPrimaryKey(goodsid);
	}

	public PageInfo<Goods> getGoodsListByParam(Map<String, Object> param,int page,int rows) {
		PageHelper.startPage(page,rows);//分页插件
		//查询商品
		List<Goods> goodsList=goodsMapper.getGoodsListByParam(param);
		Object tid = param.get("typeid");
		if(tid!=null){
			int fatherTypeid = Integer.parseInt(tid.toString());
			List<GoodsType> list =goodsTypeMapper.getChildGoodsTypes(fatherTypeid);
			for (GoodsType g : list) {
				param.put("typeid", g.getTypeid());
				List<Goods> childGoods=goodsMapper.getGoodsListByParam(param);
				goodsList.addAll(childGoods);
			}
		}
		PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
		return pageInfo;
		
	}


	@Transactional
	public void updateManyGoodsStatus(List<Goods> goodsList,Integer status,UserInfo user) {
		for (Goods goods : goodsList) {
			goods.setStatus(status);
			goods.setLastOpUser(user.getTrueName());
			goods.setLastOpDate(new Date());
			updateGoodsStatus(goods);
		}
	}
	

	//=======================================商品导入=================================
	public Goods getGoodsByName(String goodsName) {
		return goodsMapper.getGoodsByName(goodsName);
	}
	
	@Transactional
	public void addImportGoods(Goods goods) {
		 goodsMapper.addGoods(goods);
	}
}
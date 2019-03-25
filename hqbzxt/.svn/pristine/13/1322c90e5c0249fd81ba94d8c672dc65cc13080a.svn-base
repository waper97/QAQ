package com.yj.hqbz.controller.goods;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.hqbz.model.goods.Goods;
import com.yj.hqbz.model.goods.GoodsSaleInfo;
import com.yj.hqbz.services.goods.GoodsSKUService;
import com.yj.hqbz.services.goods.GoodsService;
import com.yj.hqbz.services.goods.OrgSkuService;
import com.yj.hqbz.util.StringUtil;


/**
 * 商品相关类查询
 * @Title: GoodsQueryController.java
 * @Package com.yj.hqbz.controller.goods
 * @Description: TODO
 * @author xx
 * @date 2019-3-6
 */
@RestController
public class GoodsQueryController extends BaseController{
	@Resource
	GoodsService goodsService;
	@Resource
	OrgSkuService orgSkuService;
	
	/**
	 * 获取商品在售列表
	 * @param param
	 * @return
	 */
	@RequestMapping("/goods/common/goodsList")
	public Object getOnSaleGoodsList(String goodsTypeid,String keywords,DataGridModel grid){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("goodsTypeid", goodsTypeid);
		param.put("keywords", keywords);
		param.put("customerOrgId", getTokenUser().getOrgId());		
		param.put("orderBy", grid.getOrderBy());
		param.put("orderType", grid.getOrderType());		
		
		PageInfo<Goods> pg = goodsService.getCustomerCanBuyGoodsListByParam(param, grid.getPage(), grid.getRows());		
		return new BaseRes("Ok",pg.getTotal(),pg.getPages(),pg.getList());
	}
	

	/**
	 * 获取在售商品详情,只与商家是否上下架有关
	 * @param googid
	 * @return
	 */
	@RequestMapping("/goods/common/getGoodsSaleInfo")
	public Object getGoodsSaleDetailInfo(String goodsid){
		if(StringUtil.isBlank(goodsid)){
			return fail("参数非法");
		}
		else{
			//首先取得商品基本信息
			GoodsSaleInfo info = orgSkuService.getGoodsSaleDetailInfo(goodsid, getTokenUser().getOrgId());
			if(info == null)
				return fail("该商品看不见了");
			else
				return success(info);
				
		}
	}
	
	
	@RequestMapping("/goods/common/getOrgGoodsSaleSpecList")
	public Object getGoodsSaleDetailInfo(String skuid,Integer orgid){
		if(StringUtil.isBlank(skuid) || orgid == null){
			return fail("参数非法");
		}
		else{
			return success(orgSkuService.getOrgSkuBySkuIdAndOrgId(skuid, orgid));
		}
	}

}

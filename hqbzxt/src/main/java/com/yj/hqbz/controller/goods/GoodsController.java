/**   
* 
*/
package com.yj.hqbz.controller.goods;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.hqbz.model.goods.Goods;
import com.yj.hqbz.model.goods.GoodsPic;
import com.yj.hqbz.model.goods.GoodsSKU;
import com.yj.hqbz.model.goods.GoodsType;
import com.yj.hqbz.model.goods.OrgSku;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.goods.GoodsPicInfoService;
import com.yj.hqbz.services.goods.GoodsSKUService;
import com.yj.hqbz.services.goods.GoodsService;
import com.yj.hqbz.services.goods.GoodsTypeService;
import com.yj.hqbz.services.goods.GoodsUnitService;
import com.yj.hqbz.services.goods.OrgSkuService;
import com.yj.hqbz.util.StringUtil;
import com.yj.hqbz.util.UUIDUtil;

/**   
 * @Title: GoodsController.java
 * @Package com.yj.hqbz.controller.goods 
 * @Description: TODO
 * @author xx   
 * @date 2019-2-27 
 */
@RestController
public class GoodsController extends BaseController {
	@Resource
	GoodsService goodsService;
	@Resource
	GoodsSKUService goodsSKUService;
	@Resource
	GoodsPicInfoService goodsPicInfoService;
	@Resource
	GoodsUnitService goodsUnitService;
	@Resource
	GoodsTypeService goodsTypeService;
	@Resource
	OrgSkuService orgSkuService;
	
	/**
	 * 获取商品基本单位列表
	 * @return
	 */
	@GetMapping("/goods/manage/getGoodsUnitList")
	public Object getGoodsUnitList(){
		return success(goodsUnitService.getGoodsUnitList());
	}
	
	/**
	 * 获取云仓商品列表
	 * [{
	    goodsID:"ID",
	    code:"编码",
	    goodsTypeName:"商品分类",
	    name:"商品名称",
	    aliasName:"商品别名",
	    unit:"基本单位",
	    spec:"规格 例如 4/5 表示该商品下有5个规格，启用的规格数有4个",
	    status:"状态 0 启用 1停用"
  		}]
	 */
	@GetMapping("/goods/sku/getGoodsList")
	public Object getGoodsList(String typeid,String businessAttr,String code,String name,String status, DataGridModel grid){
		Map<String,Object> param=new HashMap<>();
		if(StringUtils.isBlank(typeid)){
		    param.put("typeid", null);
		}else{
		    param.put("typeid", Integer.parseInt(typeid));
		}
		if(StringUtils.isBlank(businessAttr)){
		    param.put("businessAttr", null);
		}else{
		    param.put("businessAttr", Integer.parseInt(businessAttr));
		}	
		if(StringUtils.isBlank(code)){
		    param.put("code", null);
		}else{
		    param.put("code", code);
		}
		
		param.put("name", name);
		param.put("orderBy", grid.getOrderBy());
		param.put("orderType", grid.getOrderType());
		param.put("status", status);//可查询停用、启用的，不能查询已删除的（2）
		PageInfo<Goods> goodsList = goodsService.getGoodsListByParam(param,grid.getPage(),grid.getRows());
		List<Goods> list = goodsList.getList();
		for (Goods goods : list) {
			//商品分类 例如：XX/XXX/XXXX
			StringBuffer sb=new StringBuffer();
			List<GoodsType> tree = goodsTypeService.getFatherTree(goods.getGoodsTypeid());
			if(tree.size()>1){
				for(int i = tree.size()-1;i >= 0;i--){
					if(i!=0)
						sb.append(tree.get(i).getTypeName()+"/");
					else {
						sb.append(tree.get(i).getTypeName());
					}
				}
				goods.setGoodsTypeName(sb.toString());
			}else if(tree.size()==1) {
				goods.setGoodsTypeName(tree.get(0).getTypeName());
			}
			//获取规格数（启用/全部，例如：4/5）
			String spec=goodsSKUService.getGoodsSpecByGoodsId(goods.getGoodsid());
			goods.setSpec(spec);
		}
		return new BaseRes("OK",goodsList.getTotal(),goodsList.getPages(),list);
	}
	
	/**
	 * @param sb
	 * @param goodstypeid
	 */
//	private void getGoodsTypeStr(StringBuffer sb, Integer goodstypeid) {
//		GoodsType goodsType = goodsTypeService.selectByPrimaryKey(goodstypeid);
//		String typename = goodsType.getTypeName();
//		String strType="";
//		if(goodsType.getParentid()!=0){
//			strType=sb.toString()+"/"+typename;
//			sb.append(strType);
//			getGoodsTypeStr(sb, goodsType.getParentid());
//		}else if(goodsType.getParentid()==0){
//			strType=typename+sb.toString();
//			sb.setLength(0);
//			sb.append(strType);
//			return;
//		}
//	}
	
	
	/**
	 * 新增云仓商品
	 * 1、当修改时，goodsid和skuid必填，
	 * 2、影响商品表，商品SKU表,商品图片表
	 */
	@PostMapping("/goods/sku/saveGoods")
	public Object saveGoods(@RequestBody Goods goods){
		UserInfo tokenUser = getTokenUser();
		String userName = tokenUser.getTrueName();

		//为新增
		//商品信息
		goods.setCreator(userName);//创建人
		//商品拼音码（助记码）
		goods.setNamepy(getPinYin(null, goods.getGoodsName()));
		//存入随机UUID字符串id
		String goodsid = UUIDUtil.getUUID();
		goods.setGoodsid(goodsid);
		goods.setStatus(0);
		goodsService.addGoods(goods);
		return success("保存成功！");
	}
	
	/**
	 * 修改保存商品
	 * 1、当修改时，goodsid和skuid必填，
	 * 2、影响商品表，商品SKU表,商品图片表
	 */
	@PostMapping("/goods/sku/updateGoods")
	public Object updateGoods(@RequestBody Goods goods){
		UserInfo tokenUser = getTokenUser();
		String userName = tokenUser.getUserName();
		//为修改
		//商品信息
		if(StringUtil.isBlank(goods.getGoodsid())){
			return fail("商品id为空，修改失败！");
		}
		goods.setLastOpUser(userName);//修改人
		//商品拼音码（助记码）
		goods.setNamepy(getPinYin(null, goods.getGoodsName()));
		goodsService.updateGoods(goods);
		return success("修改成功！");
	}
	
	
	/**
	 * 启用/停用云仓商品
	 * 1、0：启用 1：停用 
	 * 2、商品停用后，其子规格一并停用
	 */
	@PostMapping("/goods/sku/updateGoodsStatus")
	public Object updateGoodsStatus(String goodsid,Integer status){
		Goods goods = goodsService.selectByPrimaryKey(goodsid);
		if(goods==null){
			return fail("商品不存在，操作失败！");
		}
		if(status==null){
			return fail("状态不能为空，操作失败！");
		}
		if(goods.getStatus()==2){
			return fail("商品已经删除，操作失败！");
		}
		String flag = status==0  ?"启用":"停用";
		if(goods.getStatus().intValue()==status.intValue()){
			return fail("商品已经"+flag+"，操作重复！");
		}
		//如果是停用，判断是否有机构在使用，如果有使用则不能停用
		if(status.intValue()==1){
			List<OrgSku> orgSkuGoods = orgSkuService.getOrgSkuByGoodsId(goodsid);
			if(orgSkuGoods.size()>0){
				return fail("有商家在使用该商品，停用失败！");
			}
		}
		goods.setStatus(status);
		goods.setLastOpUser(this.getTokenUser().getTrueName());
		goods.setLastOpDate(new Date());
		goodsService.updateGoodsStatus(goods);
		return success(flag+"成功！");
	}
	
	/**
	 * 批量启用、停用商品
	 * 若停用的商品中有一个失败，则所有商品操作失败。
	 * 该接口未用（2019-3-20）
	 */
	@PostMapping("/goods/sku/updateManyGoodsStatus")
	public Object updateManyGoodsStatus(String[] goodsids,Integer status){
		if(status==null){
			return fail("状态不能为空，操作失败！");
		}
		if(goodsids==null){
			return fail("商品id不能为空！");
		}
		String flag = status==0  ?"启用":"停用";
		List<Goods> goodsList=new ArrayList<>();
		for (String goodsid : goodsids) {
			Goods goods = goodsService.selectByPrimaryKey(goodsid);
			if(goods==null){
				return fail("有商品不存在，操作失败！");
			}
			if(goods.getStatus()==2){
				return fail("有商品已经删除，操作失败！");
			}
			if(goods.getStatus().intValue()==status.intValue()){
				return fail("有商品已经"+flag+"，操作重复！");
			}
			if(status.intValue()==1){
				List<OrgSku> orgSkuGoods = orgSkuService.getOrgSkuByGoodsId(goodsid);
				if(orgSkuGoods.size()>0){
					return fail("有商家在使用其中的商品，停用失败！");
				}
			}
			goodsList.add(goods);
		}
		
		goodsService.updateManyGoodsStatus(goodsList,status,getTokenUser());
		return success(flag+"成功！");
	}
	
	/**
	 * 删除云仓商品
	 * 1、已停用，且没有商家上架该商品才允许删除
	 * 商家有没有上架该商品
	 */
	@PostMapping("/goods/sku/deleteGoods")
	public Object deleteGoods(String goodsid){
		
		List<GoodsSKU> list=goodsSKUService.getGoodsSKUByGoodsId(goodsid);
		if(list.size()>0){
			return fail("商品还有规格，删除失败！");
		}
		Goods goods = goodsService.selectByPrimaryKey(goodsid);
		if(goods.getStatus()==0){
			return fail("商品还未停用，删除失败！");
		}
		if(goods.getStatus()==2){
			return fail("商品已经删除，不允许重复删除！");
		}
		
		List<OrgSku> olist=orgSkuService.getOrgSkuByGoodsId(goodsid);
		if(olist.size()>0){
			return fail("还有商家上架了该商品，删除失败！");
		}
		goods.setStatus(2);
		goods.setLastOpUser(getTokenUser().getTrueName());
		goods.setLastOpDate(new Date());
		goodsService.deleteGoods(goods);
		return success("删除成功！");
	}
	
	
	/**
	 * 删除云仓商品规格
	 * 1、只有当商品规格已经停用且没有商家上架该商品规格时才能删除
	 * 2、商品上架状态在供货商SKU表
	 */
	@PostMapping("/goods/sku/deleteGoodsSku")
	public Object deleteGoodsSKU(String skuid){
		GoodsSKU goodsSKU = goodsSKUService.selectByPrimaryKey(skuid);
		if(goodsSKU==null){
			return fail("商品规格不存在！");
		}
		if(goodsSKU.getStatus()==0){
			return fail("商品规格没有停用，删除失败！");
		}
		if(goodsSKU.getStatus()==2){
			return fail("商品规格已经删除，不允许重复删除！");
		}
		//没有商家上架该商品
		List<OrgSku> list=orgSkuService.getOrgSkuBySkuId(skuid);
		if(list.size()>0){
			return fail("还有商家上架了该商品，删除失败！");
		}
		goodsSKU.setLasteDitor(getTokenUser().getTrueName());
		goodsSKU.setLasteDitDate(new Date());
		goodsSKU.setStatus(2);
		goodsSKUService.deleteGoodsSku(goodsSKU);
		return success("删除成功！");
	}
	
	
	/**
	 * 启用/停用商品规格
	 * 1、0：启用 1：停用
	 */
	@PostMapping("/goods/sku/updateGoodsSkuStatus")
	public Object updateGoodsSKUStatus(String skuid,Integer status){
		GoodsSKU goodsSKU = goodsSKUService.selectByPrimaryKey(skuid);
		if(goodsSKU==null){
			return fail("商品规格不存在，操作失败！");
		}
		if(status==null){
			return fail("status不能为空，操作失败！");
		}
		String flag = status==0  ?"启用":"停用";
		if(goodsSKU.getStatus()==status){
			return fail("商品规格已经"+flag+"，操作失败！");
		}
		goodsSKU.setLasteDitor(getTokenUser().getTrueName());
		goodsSKU.setLasteDitDate(new Date());
		goodsSKU.setStatus(status);
		goodsSKUService.updateGoodsSKUStatus(goodsSKU);
		return success(flag+"成功！");
	}
	
	/**
	 * 获取商品详情
	 */
	@GetMapping("/goods/sku/getGoodsInfo")
	public Object getGoodsInfo(String goodsid){
		Goods goods = goodsService.selectByPrimaryKey(goodsid);
		if(goods==null){
			return fail("没有查询到商品！");
		}
		if(goods.getGoodsTypeid()!=null){
			GoodsType goodsType = goodsTypeService.selectByPrimaryKey(goods.getGoodsTypeid());
			goods.setGoodsTypeName(goodsType.getTypeName());
		}
		
		GoodsPic goodsPic=goodsPicInfoService.getGoodsPicByGoodsId(goodsid,null);
		if(goodsPic!=null){
			goods.setPicUrl(goodsPic.getPicUrl());
			goods.setThumbnailUrl(goodsPic.getThumbnailUrl());
		}
		List<GoodsSKU> skus = goodsSKUService.getGoodsSKUByGoodsId(goodsid);
		if(skus.size()>0){
			List<GoodsSKU> skuList = new ArrayList<>();
			for (GoodsSKU g : skus) {
				GoodsPic pic = goodsPicInfoService.getGoodsPicByGoodsId(goodsid, g.getSkuid());
				g.setPicUrl(pic.getPicUrl());
				g.setThumbnailUrl(pic.getThumbnailUrl());
				skuList.add(g);
			}
			goods.setSkuList(skuList);
		}
		return success(goods);
	}
	
}

/**   
* 
*/
package com.yj.hqbz.controller.goods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.expr.NewArray;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.hqbz.model.goods.Goods;
import com.yj.hqbz.model.goods.GoodsType;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.goods.GoodsService;
import com.yj.hqbz.services.goods.GoodsTypeService;
import com.yj.hqbz.services.goods.GoodsUnitService;

/**   
 * @Title: GoodsTypeController.java
 * @Package com.yj.hqbz.controller.goods 
 * @Description: TODO
 * @author xx   
 * @date 2019-2-27 
 */
@RestController
public class GoodsTypeController extends BaseController {

	@Resource
	GoodsTypeService goodsTypeService;
	@Resource
	GoodsService goodsService;
	@Resource
	GoodsUnitService goodsUnitService;
	
	/**
	 * 获取商品分类树
	 * 
	 */
	@GetMapping("/goods/common/getGoodsTypeTree")
	public Object getGoodsTypeTree(Integer includeDisabled,Integer businessAttr){
		return success(goodsTypeService.getGoodsTypeTree(includeDisabled,businessAttr));
	}
	
	/**
	 * 获取商品分类列表
	 */
	@GetMapping("/goods/common/getGoodsTypeList")
	public Object getGoodsTypeByParent(Integer parentId,Integer businessAttr,
			Integer includeDisabled,DataGridModel model){
		if(parentId == null || includeDisabled == null){
			return fail("参数非法！");
		}
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("parentId", parentId);
		param.put("includeDisabled", includeDisabled);
		param.put("orderBy", model.getOrderBy());
		param.put("orderType", model.getOrderType());
		
		PageInfo<GoodsType> pageInfo=goodsTypeService.getGoodsTypeByParam(param,model.getPage(),model.getRows());
		
		return new BaseRes("OK",pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
	}
	
	/**
	 * 新增保存商品分类
	 * @param goodsType
	 */
	@PostMapping("/goods/manage/addSaveGoodsType")
	public Object addGoodsType(GoodsType goodsType){
		UserInfo tokenUser = getTokenUser();
		goodsType.setCreator(tokenUser.getTrueName());
		if(goodsType.getParentid()!=null && goodsType.getParentid()!=0){
			//业务属性，当为二级分类时，保存默认的一级分类值
			GoodsType parent = goodsTypeService.selectByPrimaryKey(goodsType.getParentid());
			goodsType.setBusinessAttr(parent.getBusinessAttr());
		}else{
			//上级分类id，当为顶级（一级）时，默认存入为0
			goodsType.setParentid(0);
		}
		goodsTypeService.addGoodsType(goodsType);			
		
		return success("保存成功！");
	}
	
	/**
	 * 修改保存商品分类
	 * @param goodsType
	 */
	@PostMapping("/goods/manage/udpateSaveGoodsType")
	public Object udpateSaveGoodsType(GoodsType goodsType){
		UserInfo tokenUser = getTokenUser();
		goodsType.setLastOpUser(tokenUser.getTrueName());
		goodsTypeService.updateGoodsType(goodsType);
		return success("保存成功！");
	}
	
	/**
	 * 删除商品分类
	 * 1、逻辑删除
	 * 2、仅当分类下面没有子类且没有商品时才允许删除
	 * 3、删除商品分类会影响排序号！
	 * @param typeId
	 */
	@PostMapping("/goods/manage/deleteGoodsType")
	public Object deleteGoodsType(Integer typeid){
		List<GoodsType> children = goodsTypeService.getGoodsTypesByParentId(typeid,null,1);
		Integer count  = goodsService.getGoodsCountByGoodsType(typeid);
		if(children.size()>0){
			return fail("该分类下有子分类，不允许删除！");
		}else if(count>0){
			return fail("该分类已有商品，不允许删除！");
		}
		goodsTypeService.deleteGoodsType(typeid);
		
		return success("删除成功！");
	}
	
	/**
	 * 停用/启用商品分类
	 */
	@PostMapping("/goods/manage/disableGoodsType")
	public Object disableGoodsType(int id,int status){
		goodsTypeService.updateGoodsTypeStatus(id,status);
		return success("修改成功！");
	}
	
	/**
	 * 修改商品分类排序
	 * 1、sort为0时升序，为1时降序
	 * 2、在同一级中，第一个分类不允许升，最后一个不允许降。
	 */
	@PostMapping("/goods/manage/updateGoodsTypeSortNo")
	public Object updateGoodsTypeSortNo(int typeid,int sort){
		GoodsType goodsType = goodsTypeService.selectByPrimaryKey(Integer.valueOf(typeid));
		if(goodsType == null){
			return fail("操作非法！");
		}
		else{
			GoodsType switchGoodsType = goodsTypeService.selectSwitchGoodsTypeById(goodsType,sort);
			if(switchGoodsType == null){
				return fail("该分类不能进行排序调整");
			}
			else{
				int oldSortNo = goodsType.getSortNo();
				goodsType.setSortNo(switchGoodsType.getSortNo());
				switchGoodsType.setSortNo(oldSortNo);
				
				goodsTypeService.updateSortno(goodsType,switchGoodsType);
				
			}
		}		
		return success("修改成功！");
	}
	
	/**
	 * 根据分类id获取上级至顶层分类
	 */
	@GetMapping("/goods/common/getFatherTree")
	public Object getFatherTree(Integer typeid){
		
		List<GoodsType> map=goodsTypeService.getFatherTree(typeid);
		return success(map);
	}
	
}

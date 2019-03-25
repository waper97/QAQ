/**   
* 
*/
package com.yj.hqbz.services.goods;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.goods.GoodsType;

/**   
 * @Title: GoodsTypeService.java
 * @Package com.yj.hqbz.services.goods 
 * @Description: TODO
 * @author xx   
 * @date 2019-2-27 
 */
public interface GoodsTypeService {
	//新增商品分类
	void addGoodsType(GoodsType goodsType);
	//根据id删除商品分类
	void deleteGoodsType(Integer id);
	//修改商品分类
	void updateGoodsType(GoodsType goodsType);
	//查询所有商品分类
	List<GoodsType> getAllGoodsTypes();
	//根据排序号调整排序
	void updateSortno(GoodsType type1,GoodsType type2);
	//根据一级分类id查询二级分类
	List<GoodsType> getGoodsTypesByParentId(Integer parentId,Integer businessAttr,Integer includeDisabled);
	//根据主键查询商品分类
	GoodsType selectByPrimaryKey(Integer typeid);
	
	//根据分类节点取得要与该节点交换的节点
	GoodsType selectSwitchGoodsTypeById(GoodsType type,int sortType);
	//查询所有一级分类
	List<GoodsType> getParentTypes();
	//根据排序id查询商品分类
	GoodsType selectBySortNo(String sortno);
	//获取商品分类树
	List<GoodsType> getGoodsTypeTree(Integer includeDisabled,Integer businessAttr);
	
	GoodsType getGoodsTypeBySortNo(String checkSortNO);
	
	void updateGoodsTypeStatus(int id, int status);
	
	List<GoodsType> getFatherTree(Integer typeid);

	PageInfo<GoodsType> getGoodsTypeByParam(Map<String, Object> param,
			int page, int rows);
}

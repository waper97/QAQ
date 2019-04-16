package com.yj.hqbz.mapper.goods;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.goods.GoodsType;
import org.apache.ibatis.annotations.Param;

public interface GoodsTypeMapper {
	//根据主键查询商品分类
  	GoodsType selectByPrimaryKey(Integer typeid);
  	
  	//根据商品分类ID取得该分类对应的子分类最大序号
  	String selectMaxSortNoByParentTypeId(Integer parentId);
  	
	//新增商品分类
  	void addGoodsType(GoodsType goodsType);
  	//根据id删除商品分类
  	void deleteGoodsType(Integer id);
  	//修改商品分类
  	void updateGoodsType(GoodsType goodsType);
  	//查询所有商品分类
  	List<GoodsType> getAllGoodsTypes();
  	//根据排序号调整排序
  	void updateSortno(GoodsType type);
  	//根据一级分类id查询二级分类
  	List<GoodsType> getGoodsTypesByParentId(Map<String,Object> param);
  	//查询顶级分类
	List<GoodsType> getParentTypes();
	//根据排序查询商品分类
	GoodsType selectBySortNo(String sortno);
	
	GoodsType selectSwitchGoodsType(Map<String,Object> param);

	void updateGoodsTypeStatus(int id, int status);
	
	void disabledChildrenGoodsType(Integer parentId);

	List<GoodsType> getChildGoodsTypes(int typeid);

	List<GoodsType> getFatherTree(Integer typeid);

}
/**   
* 
*/
package com.yj.hqbz.services.impl.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.goods.GoodsTypeMapper;
import com.yj.hqbz.model.goods.GoodsType;
import com.yj.hqbz.services.goods.GoodsTypeService;
import com.yj.hqbz.util.StringUtil;

/**   
 * @Title: GoodsTypeServiceImpl.java
 * @Package com.yj.hqbz.services.impl.goods 
 * @Description: TODO
 * @author xx   
 * @date 2019-2-27 
 */
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {
	
	@Resource
	GoodsTypeMapper goodsTypeMapper;
	
	@Override
	@Transactional
	public void addGoodsType(GoodsType goodsType) {
		/**
		 * 分类排序号
		 * 1、顶级排序号：1 2 3
		 * 2、下级排序号与上级相关，如上级为1，下级为：1-1、1-2...
		 */
		
		String maxSortNo = goodsTypeMapper.selectMaxSortNoByParentTypeId(goodsType.getParentid());
		if(StringUtil.isBlank(maxSortNo)){
			maxSortNo = "1";
		}
		else{
			maxSortNo = String.valueOf(Integer.parseInt(maxSortNo)+1);
			
		}
		goodsType.setSortNo(Integer.parseInt(maxSortNo));
		goodsTypeMapper.addGoodsType(goodsType);
	}
	
	public GoodsType selectSwitchGoodsTypeById(GoodsType type,int sortType){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("parentId", type.getParentid());
		param.put("sortno", type.getSortNo());
		param.put("sortType", sortType);
		
		return goodsTypeMapper.selectSwitchGoodsType(param);
	}

	
	@Override
	@Transactional
	public void deleteGoodsType(Integer id) {
		goodsTypeMapper.deleteGoodsType(id);		
	}

	@Override
	@Transactional
	public void updateGoodsType(GoodsType goodsType) {
		//修改时，不涉及排序号，上级分类不允许修改。
		goodsTypeMapper.updateGoodsType(goodsType);
	}

	@Override
	public List<GoodsType> getAllGoodsTypes() {
		return goodsTypeMapper.getAllGoodsTypes();
	}

	@Override
	@Transactional
	public void updateSortno(GoodsType type1,GoodsType type2) {
		goodsTypeMapper.updateSortno(type1);
		goodsTypeMapper.updateSortno(type2);
	}
	
	
	@Override
	public List<GoodsType> getGoodsTypesByParentId(Integer parentId,Integer businessAttr,Integer includeDisabled) {
		Map<String,Object> param = new HashMap<String,Object>();
		if(parentId!=null)
			param.put("parentId", parentId);
		if(businessAttr!=null)
			param.put("businessAttr", businessAttr);
		if(includeDisabled!=null)
			param.put("includeDisabled", includeDisabled.toString());
		return goodsTypeMapper.getGoodsTypesByParentId(param);
	}
	
	@Override
	public PageInfo<GoodsType> getGoodsTypeByParam(Map<String, Object> param,
			int page, int rows) {
		List<GoodsType> temp = new ArrayList<>();
		PageHelper.startPage(page,rows);
		List<GoodsType> list = goodsTypeMapper.getGoodsTypesByParentId(param);
		if(!list.isEmpty()){
			for(GoodsType goods : list){
				List<GoodsType> type = getIsExistChilden(Integer.valueOf(goods.getTypeid())); //是否有下一级
				if(!type.isEmpty()){
					goods.setChildren(temp);
				}
			}
		}
		PageInfo<GoodsType> pageInfo=new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public GoodsType selectByPrimaryKey(Integer typeid) {
		return goodsTypeMapper.selectByPrimaryKey(typeid);
	}

	@Override
	public List<GoodsType> getParentTypes() {
		return goodsTypeMapper.getParentTypes();
	}

	@Override
	public GoodsType selectBySortNo(String sortno) {
		return goodsTypeMapper.selectBySortNo(sortno);
	}

	@Override
	public List<GoodsType> getGoodsTypeTree(Integer includeDisabled,Integer businessAttr) {
		return createTypeTree(0,includeDisabled,businessAttr);
	}
	
	private List<GoodsType> createTypeTree(Integer parent,Integer includeDisabled,Integer businessAttr){
		List<GoodsType> list = getGoodsTypesByParentId(parent,businessAttr,includeDisabled);
		for(GoodsType type:list){
			type.setChildren(createTypeTree(type.getTypeid(),includeDisabled,businessAttr));
		}
		return list;
	}
	

	@Override
	public GoodsType getGoodsTypeBySortNo(String checkSortNO) {
		return goodsTypeMapper.selectBySortNo(checkSortNO);
	}

	@Override
	public void updateGoodsTypeStatus(int id, int status) {
		goodsTypeMapper.updateGoodsTypeStatus(id,status);
		if(status==1){
			goodsTypeMapper.disabledChildrenGoodsType(id);
		}
	}

	@Override
	public List<GoodsType> getFatherTree(Integer typeid) {
		return goodsTypeMapper.getFatherTree(typeid);
	}





	private List<GoodsType> getIsExistChilden(Integer parentid){
		return  goodsTypeMapper.getIsExistChilden(parentid);
	}
}

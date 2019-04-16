/**   
* 
*/
package com.yj.hqbz.services.impl.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	
	@Transactional
	public void deleteGoodsType(Integer id) {
		goodsTypeMapper.deleteGoodsType(id);		
	}

	@Transactional
	public void updateGoodsType(GoodsType goodsType) {
		//修改时，不涉及排序号，上级分类不允许修改。
		goodsTypeMapper.updateGoodsType(goodsType);
	}

	public List<GoodsType> getAllGoodsTypes() {
		return goodsTypeMapper.getAllGoodsTypes();
	}

	@Transactional
	public void updateSortno(GoodsType type1,GoodsType type2) {
		goodsTypeMapper.updateSortno(type1);
		goodsTypeMapper.updateSortno(type2);
	}
	
	
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
	
	public PageInfo<GoodsType> getGoodsTypeByParam(Map<String, Object> param,
			int page, int rows) {
		PageHelper.startPage(page,rows);
		List<GoodsType> list = goodsTypeMapper.getGoodsTypesByParentId(param);
		for (GoodsType type : list) {
			param.put("parentId", type.getTypeid());
			List<GoodsType>  children = goodsTypeMapper.getGoodsTypesByParentId(param);
			if(children!=null&&children.size()>0) {
				type.setChildren(children);
			}
		}
		PageInfo<GoodsType> pageInfo=new PageInfo<>(list);
		return pageInfo;
	}

	public GoodsType selectByPrimaryKey(Integer typeid) {
		return goodsTypeMapper.selectByPrimaryKey(typeid);
	}

	public List<GoodsType> getParentTypes() {
		return goodsTypeMapper.getParentTypes();
	}

	public GoodsType selectBySortNo(String sortno) {
		return goodsTypeMapper.selectBySortNo(sortno);
	}

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
	

	public GoodsType getGoodsTypeBySortNo(String checkSortNO) {
		return goodsTypeMapper.selectBySortNo(checkSortNO);
	}

	public void updateGoodsTypeStatus(int id, int status) {
		goodsTypeMapper.updateGoodsTypeStatus(id,status);
		if(status==1){
			goodsTypeMapper.disabledChildrenGoodsType(id);
		}
	}

	public List<GoodsType> getFatherTree(Integer typeid) {
		return goodsTypeMapper.getFatherTree(typeid);
	}



}
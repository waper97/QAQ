package com.yj.hqbz.services.schoolOperation;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.schoolOperation.FoodAdditive;
import org.apache.ibatis.annotations.Param;

import java.util.Map;


/**
 * 
 * @author wangpeng
 * @date 2019-3-14
 */

public interface FoodAdditiveService {
	
	/**
	 * 
	 * @param param
	 * @param page 
	 * @param row
	 * @return
	 */
	PageInfo<FoodAdditive> getFoodAdditiveList(Map<String, Object> param, int page, int row);


	void insert(FoodAdditive record);

	void insertSelective(FoodAdditive record);

	FoodAdditive selectByPrimaryKey(String id);

	void updateByPrimaryKeySelective(FoodAdditive record);

	void updateByPrimaryKey(FoodAdditive record);


	Map<String,Object>  getFoodAdditiveUseDetail(String code);
	
}

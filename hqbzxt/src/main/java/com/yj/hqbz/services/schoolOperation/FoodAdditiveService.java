package com.yj.hqbz.services.schoolOperation;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.schoolOperation.FoodAdditive;
import com.yj.hqbz.model.schoolOperation.FoodAdditiveOut;

/**  
 * @Title: FoodAdditiveService.java
 * @Package com.yj.hqbz.services.schoolOperation
 * @Description: TODO
 * @author xx
 * @date 2019-3-15
 */
public interface FoodAdditiveService {

	void deleteFoodAdditive(String id);

	void insertFoodAdditive(FoodAdditive record);

    FoodAdditive selectFoodAdditiveDetailInfo(String id);

    void updateFoodAdditive(FoodAdditive record);
    
    //获取食品添加时使用记录
    PageInfo<FoodAdditive> selectFoodAdditiveListByParam(Map<String,Object> param,int page,int rows);
    
    //获取食品添加剂出库(未使用完)记录
    List<FoodAdditiveOut> getFoodAdditiveOutListByParam(Map<String,Object> param);

}
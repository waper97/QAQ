package com.yj.hqbz.mapper.schoolOperation;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.schoolOperation.FoodAdditive;
import com.yj.hqbz.model.schoolOperation.FoodAdditiveOut;

public interface FoodAdditiveMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(FoodAdditive record);

    FoodAdditive selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FoodAdditive record);
    
    //获取食品添加时使用记录
    List<FoodAdditive> selectFoodAdditiveListByParam(Map<String,Object> param);
    
    //获取食品添加剂出库(未使用完)记录
    List<FoodAdditiveOut> getFoodAdditiveOutListByParam(Map<String,Object> param);
}
package com.yj.hqbz.mapper.schoolOperation;

import com.yj.hqbz.model.schoolOperation.FoodAdditive;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FoodAdditiveMapper {
    //添加食品添加剂使用记录
    int insert(FoodAdditive record);

    int insertSelective(FoodAdditive record);
    //获取食品添加剂出库记录
    FoodAdditive selectByPrimaryKey(String id);
    //修改食品添加剂出库记录
    int updateByPrimaryKeySelective(FoodAdditive record);

    int updateByPrimaryKey(FoodAdditive record);
    //查询食品添加剂使用记录
    List<FoodAdditive> getFoodAdditiveList(Map<String, Object> param);
    //查看商品添加剂使用记录详情
    Map<String,Object>  getFoodAdditiveUseDetail(@Param("code")String code);

}
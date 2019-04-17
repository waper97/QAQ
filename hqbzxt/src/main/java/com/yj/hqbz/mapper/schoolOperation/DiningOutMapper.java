package com.yj.hqbz.mapper.schoolOperation;

import com.yj.hqbz.model.schoolOperation.DiningOut;
import com.yj.hqbz.model.schoolOperation.StatisticsDiningOut;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DiningOutMapper {

    int insert(DiningOut record);

    int updateByPrimaryKey(DiningOut record);

	List<DiningOut> getListByParam(Map<String, Object> param);

	DiningOut selectByPrimaryKey(@Param("id") String id, @Param("menuCode") String menuCode);
	//统计进入计划出餐
	List<StatisticsDiningOut> diningOutOfDailyPlanByOrgid(@Param("orgid")String orgid);
	//统计今日实际出餐
	List<StatisticsDiningOut> diningOutOfDailyActualByOrgid(@Param("orgid")String orgid);

	void deleteById(DiningOut diningOut);
}
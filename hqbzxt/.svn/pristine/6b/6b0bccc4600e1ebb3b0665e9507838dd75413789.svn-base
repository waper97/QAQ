package com.yj.hqbz.services.trace;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.org.Organization;

public interface TraceService {
	Map<String,Object> getAllSchool();
	
	List<Map<String,Object>> getOrgBySchool(Integer schoolid);
	
	Map<String,Object> getFoodBySchool(Map<String,Object> map);
	
	List<Map<String,Object>> getTraceDetail(String traceid,String time);
	
	List<Map<String,Object>> getSchoolStock(String goodsTypeid,Integer areaid);
	
	List<Map<String,Object>> getExpireFood(Map<String,Object> map);
	
	PageInfo<Map<String,Object>> getMaterial(Map<String,Object> param,int page,int rows);
	
	Map<String,Object> getCountByStore(Integer orgid);
	
	PageInfo<Map<String,Object>> getOrderInfoByStore(Map<String,Object> param,int page,int rows);
	
	PageInfo<Map<String,Object>> getStoreRanking(int page,int rows);
}

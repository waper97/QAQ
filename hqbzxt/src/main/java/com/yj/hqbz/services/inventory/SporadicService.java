package com.yj.hqbz.services.inventory;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.sporadic.SporadicDetail;
import com.yj.hqbz.model.sporadic.SporadicGoods;
import com.yj.hqbz.model.sporadic.SporadicIndex;

public interface SporadicService {

	SporadicIndex getIndexByPrimaryKey(String id);
	
	PageInfo<Map<String,Object>> getInStockBySporadic(Map<String,Object> param,int page,int rows);
	
	PageInfo<SporadicDetail> getSporadicList(Map<String,Object> param,int page,int rows);
	
	SporadicDetail getSporadicDetail(String detailid);
	
	void addSporadic(SporadicIndex index,SporadicGoods goods,SporadicDetail sporadic);
	
	SporadicGoods getGoodsByIndexid(String indexid);
	
	void updateSporadic(SporadicIndex index,SporadicGoods goods,SporadicDetail sporadic);
	
	int deleteSporadic(SporadicIndex index);
	
	//根据溯源ID获取零星采购主表
	SporadicIndex getSporadicIndexByTraceId(String traceid);
	
}

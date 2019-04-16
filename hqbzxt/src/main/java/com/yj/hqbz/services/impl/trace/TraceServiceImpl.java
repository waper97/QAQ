package com.yj.hqbz.services.impl.trace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.goods.GoodsTypeMapper;
import com.yj.hqbz.mapper.org.OrgBelongMapper;
import com.yj.hqbz.mapper.trace.GoodsTraceMapper;
import com.yj.hqbz.mapper.trace.GoodsTracePicMapper;
import com.yj.hqbz.model.goods.GoodsType;
import com.yj.hqbz.model.trace.GoodsTracePic;
import com.yj.hqbz.services.trace.TraceService;
import com.yj.hqbz.util.StringUtil;
@Service
public class TraceServiceImpl implements TraceService{

	@Resource
	private GoodsTraceMapper traceMapper;
	@Resource
	private GoodsTracePicMapper tracePicMapper;
	@Resource
	private OrgBelongMapper orgBelongMapper;
	@Resource
	private GoodsTypeMapper goodsTypeMapper;
	
	public Map<String, Object> getAllSchool() {
		Map<String, Object> returnMap=new HashMap<String, Object>();
		int count=0;
		List<Map<String, Object>> area = orgBelongMapper.getBelongArea();
		for (Map<String, Object> map : area) {
			List<Map<String, Object>> school = orgBelongMapper.getSchoolByArea(map);
			count+=school.size();
			map.put("school", school);
		}
		returnMap.put("count", count);
		returnMap.put("all", area);
		return returnMap;
	}
	
	public List<Map<String, Object>> getOrgBySchool(Integer schoolid) {
		return orgBelongMapper.getOrgBySchool(schoolid);
	}
	
	public Map<String, Object> getFoodBySchool(Map<String,Object> param) {
		Map<String, Object> returnMap=new HashMap<String, Object>();
		//今日早餐
		param.put("menuType", 0);
		returnMap.put("morning",traceMapper.getMenuBySchool(param));
		//今日午餐
		param.put("menuType", 1);
		returnMap.put("noon",traceMapper.getMenuBySchool(param));
		//今日晚餐
		param.put("menuType", 2);
		returnMap.put("night",traceMapper.getMenuBySchool(param));
		//添加剂
		param.put("isAdditive", 1);
		returnMap.put("additive", traceMapper.getGoodsBySchool(param));
		//原材料
		param.put("isAdditive", 0);
		returnMap.put("goods", traceMapper.getGoodsBySchool(param));
		//商家
		returnMap.put("store", traceMapper.getStoreBySchool(param));
		return returnMap;
	}
	
	
	public List<Map<String, Object>> getTraceDetail(String traceid,String time) {
		//订单、采购
		List<Map<String, Object>> list = traceMapper.getTraceByPurchase(traceid);
		//发货
		Map<String, Object> deliver = traceMapper.getTraceByDeliver(traceid);
		if(deliver!=null) {
			deliver.put("pic", tracePicMapper.getPicByTraceid(traceid));
			list.add(deliver);
		}
		//商品实物图
		List<GoodsTracePic> goodsPic = tracePicMapper.getGoodsPicByTraceid(traceid);
		//收货
		Map<String, Object> receive = traceMapper.getTraceByReceive(traceid);
		if(receive!=null) {
			receive.put("pic", goodsPic);
			list.add(receive);
		}
		//入库
		Map<String, Object> inStock = traceMapper.getTraceByInStock(traceid);
		if(inStock!=null) {
			inStock.put("pic", goodsPic);
			list.add(inStock);
		}
		
		//出库
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("traceid", traceid);
		map.put("time", time);
		List<Map<String,Object>> outStock = traceMapper.getTraceByOutStock(map);
		for (int i = 0; i < outStock.size(); i++) {
			if(i!=0) {
				outStock.get(i).put("isTransverse", 1);
			}
			outStock.get(i).put("pic", goodsPic);
			
			//使用
			String detailid = outStock.get(i).get("detailid").toString();
			List<Map<String,Object>> additiveUse= traceMapper.getAdditiveUse(detailid);
			if(additiveUse!=null&&additiveUse.size()>0) {
				for (Map<String, Object> use : additiveUse) {
					use.put("pic", goodsPic);
				}
				outStock.get(i).put("use",additiveUse);
			}else {
				List<Map<String,Object>> goodsUse= traceMapper.getGoodsUse(detailid);
				for (Map<String, Object> use : additiveUse) {
					use.put("pic", goodsPic);
				}
				outStock.get(i).put("use",goodsUse);
			}
		}
		list.addAll(outStock);
		return list;
	}
	
	public PageInfo<Map<String, Object>> getSchoolAllStock(int page, int rows) {
		PageHelper.startPage(page, rows);
		//学校
		List<Map<String, Object>> list = traceMapper.getSchoolByAllStock();
		Map<String, Object> param=new HashMap<String, Object>();
		for (Map<String, Object> map : list) {
			param.put("schoolid", map.get("schoolid"));
			map.put("stock", traceMapper.getSchoolStock(param));
		}
		PageInfo<Map<String, Object>> info=new PageInfo<Map<String, Object>>(list);
		return info;
	}
	
	public List<Map<String, Object>> getSchoolStock(String goodsTypeid,Map<String,Object> paramMap) {
		//分类
		List<String> typeArray;
		if(StringUtil.isBlank(goodsTypeid)||"0".equals(goodsTypeid)) {
			typeArray=traceMapper.getParentGoodsTypeid();
			
		}else {
			typeArray=Arrays.asList(goodsTypeid.split(","));
		}

		//学校
		List<Map<String, Object>> schoolList = orgBelongMapper.getSchoolByArea(paramMap);
		//查询参数
		Map<String, Object> param=new HashMap<String, Object>();
		for (Map<String, Object> map : schoolList) {
			param.put("schoolid", map.get("schoolid"));
			List<Map<String, Object>> typeList=new ArrayList<Map<String, Object>>();
			for (String typeid : typeArray) {
				param.put("typeid", Integer.valueOf(typeid));
				//查询分类
				GoodsType type = goodsTypeMapper.selectByPrimaryKey(Integer.valueOf(typeid));
				//组装typeList
				Map<String, Object> typeMap=new HashMap<String, Object>();
				typeMap.put("typeid", type.getTypeid());
				typeMap.put("typeName", type.getTypeName());
				typeMap.put("stock", traceMapper.getSchoolStock(param));
				typeList.add(typeMap);
			}
			map.put("typeList", typeList);
		}
		return schoolList;
	}
	
	public List<Map<String, Object>> getExpireFood(Map<String, Object> map) {
		//学校
		List<Map<String, Object>> schoolList = traceMapper.getSchoolByExpireFood(map);
		//查询参数
		for (Map<String, Object> school : schoolList) {
			map.put("schoolid", school.get("schoolid"));
			List<Map<String, Object>> typeList= traceMapper.getExpireFood(map);
			school.put("typeList", typeList);
		}
		return schoolList;
	}
	
	public PageInfo<Map<String, Object>> getMaterial(Map<String, Object> param, int page, int rows) {
		PageHelper.startPage(page, rows);
		List<Map<String, Object>> list = traceMapper.getMaterial(param);
		PageInfo<Map<String, Object>> info=new PageInfo<Map<String, Object>>(list);
		return info;
	}
	
	public Map<String, Object> getCountByStore(Integer orgid) {
		Map<String, Object> orgMap = traceMapper.getStoreInfo(orgid);
		if(orgMap!=null) {
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("orgid", orgid);
			//总次数
			orgMap.put("sumCount", traceMapper.getCountByStore(param));
			//本月
			param.put("isMonth", 1);
			orgMap.put("monthCount", traceMapper.getCountByStore(param));
			//本季
			param.put("isMonth", 0);
			param.put("isQuarter", 1);
			orgMap.put("quarterCount", traceMapper.getCountByStore(param));
		}
		return orgMap;
	}
	
	public PageInfo<Map<String, Object>> getOrderInfoByStore(Map<String, Object> param, int page, int rows) {
		PageHelper.startPage(page,rows);
		List<Map<String, Object>> list = traceMapper.getOrderInfoByStore(param);
		PageInfo<Map<String, Object>> info=new PageInfo<Map<String, Object>>(list);
		return info;
	}
	
	public PageInfo<Map<String, Object>> getStoreRanking(int page, int rows) {
		PageHelper.startPage(page,rows);
		List<Map<String, Object>> list = traceMapper.getStoreRanking();
		PageInfo<Map<String, Object>> info=new PageInfo<Map<String, Object>>(list);
		return info;
	}
}
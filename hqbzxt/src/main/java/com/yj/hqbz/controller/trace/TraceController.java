package com.yj.hqbz.controller.trace;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.hqbz.services.trace.TraceService;
import com.yj.hqbz.util.DateUtil;
import com.yj.hqbz.util.StaticUtils;
import com.yj.hqbz.util.StringUtil;
import com.yj.hqbz.web.Global;
@RestController
@RequestMapping("/trace/supervise")
public class TraceController extends BaseController{

	@Resource
	private TraceService traceService;
	
	@RequestMapping("/getAllSchool")
	public Object getAllSchool() {
		return success(traceService.getAllSchool());
	}
	
	@RequestMapping("/getOrgBySchool")
	public Object getOrgBySchool(Integer schoolid) {
		if(schoolid==null) {
			return fail("学校ID不能为空！");
		}
		return success(traceService.getOrgBySchool(schoolid));
	}
	
	
	@RequestMapping("/getFoodByOrgid")
	public Object getFoodBySchool(String orgid,String time) {
		if(StringUtil.isBlank(orgid)||StringUtil.isBlank(time)) {
			return fail("食堂ID和日期不能为空！");
		}
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("orgid", orgid);
		map.put("time", time);
		map.put("additiveTypid", Global.getSystemParamIntValueByKey(StaticUtils.S_ADDITIVE_TYPEID_CODE));
		Map<String,Object> info=traceService.getFoodBySchool(map);
		return success(info);
	}
	
	@RequestMapping("/getTraceDetail")
	public Object getTraceDetail(String traceid,String time) {
		if(StringUtil.isBlank(traceid)||StringUtil.isBlank(time)) {
			return fail("溯源ID和日期不能为空！");
		}
		return success(traceService.getTraceDetail(traceid,time));
	}
	
	@RequestMapping("/getSchoolStock")
	public Object getSchoolStock(String goodsTypeid,Integer areaid) {
		if(StringUtil.isBlank(goodsTypeid)) {
			return fail("分类不能为空！");
		}
		List<Map<String,Object>> info = traceService.getSchoolStock(goodsTypeid,areaid);
		return new BaseRes("获取成功！", info);
	}
	
	@RequestMapping("/getExpireFood")
	public Object getExpireFood(String goodsTypeid,Integer areaid,String outDate,DataGridModel model) {
		if(StringUtil.isBlank(goodsTypeid)||StringUtil.isBlank(outDate)) {
			return fail("分类、日期不能为空！");
		}
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("goodsTypeid", goodsTypeid);
		param.put("areaid", areaid);
		param.put("outDate", outDate);
		param.put("orderType", model.getOrderType());
		List<Map<String,Object>> info = traceService.getExpireFood(param);
		return new BaseRes("获取成功！", info);
	}
	
	
	@RequestMapping("/getMaterial")
	public Object getMaterial(String goodsTypeid,Integer areaid,String buyDate,DataGridModel model) {
		if(StringUtil.isBlank(buyDate)) {
			return fail("日期不能为空！");
		}
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("goodsTypeid", goodsTypeid);
		param.put("areaid", areaid);
		param.put("buyDate", buyDate);
		param.put("orderType", model.getOrderType());
		PageInfo<Map<String,Object>> info = traceService.getMaterial(param, model.getPage(), model.getRows());
		return new BaseRes("获取成功！", info.getTotal(), info.getPages(), info.getList());
	}
	
	@RequestMapping("/getCountByStore")
	public Object getCountByStore(Integer orgid) {
		if(orgid==null) {
			return fail("商家ID不能为空！");
		}
		Map<String,Object> info = traceService.getCountByStore(orgid);
		return new BaseRes("获取成功！", info);
	}
	
	@RequestMapping("/getOrderInfoByStore")
	public Object getOrderInfoByStore(Integer orgid,String beginTime,String endTime,DataGridModel model) {
		if(orgid==null||StringUtil.isBlank(beginTime)||StringUtil.isBlank(endTime) ) {
			return fail("商家ID、开始时间、结束时间不能为空！");
		}
		Date beginDate=DateUtil.getDateByStr(beginTime.trim()+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
		Date endDate=DateUtil.getDateByStr(endTime.trim()+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		if(beginDate!=null&&endDate!=null&&beginDate.after(endDate)) {
			return fail("开始时间不能小于结束时间！");
		}
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("orgid", orgid);
		param.put("beginDate", beginDate);
		param.put("endDate", endDate);
		PageInfo<Map<String,Object>> info=traceService.getOrderInfoByStore(param,model.getPage(),model.getRows());
		return new BaseRes("获取成功！", info.getTotal(), info.getPages(), info.getList());
	}
	
	
	@RequestMapping("/getStoreRanking")
	public Object getStoreRanking(DataGridModel model) {
		PageInfo<Map<String,Object>> info=traceService.getStoreRanking(model.getPage(),model.getRows());
		return new BaseRes("获取成功！", info.getTotal(), info.getPages(), info.getList());
	}
}

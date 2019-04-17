package com.yj.hqbz.controller.area;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yj.common.controller.BaseController;
import com.yj.hqbz.model.area.Area;
import com.yj.hqbz.services.area.AreaService;

@RestController
public class AreaController extends BaseController{

	@Resource
	private AreaService areaService;
	
	@RequestMapping("/common/area/getAreaByParent")
	public Object getAreaByParent(Integer parentid) {
		if(parentid==null) {
			parentid=0;
		}
		List<Area> areaList = areaService.getAreaByParent(parentid);
		return success(areaList);
	}
	
	@RequestMapping("/common/area/getAreaNameById")
	public Object getAreaNameById(Integer areaid) {
		if(areaid==null) {
			return fail("地区ID不能为空！");
		}
		String areaName = areaService.getAreaNameById(areaid);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("areaid", areaid);
		map.put("areaName", areaName);
		return success(map);
	}
}

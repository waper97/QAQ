package com.yj.hqbz.controller.area;

import java.util.List;

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
}

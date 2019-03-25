package com.yj.hqbz.services.area;

import java.util.List;

import com.yj.hqbz.model.area.Area;

public interface AreaService {
	/**根据父ID获取地区*/
	List<Area> getAreaByParent(Integer parentid);
	
}

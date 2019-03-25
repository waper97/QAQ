package com.yj.hqbz.mapper.area;

import java.util.List;

import com.yj.hqbz.model.area.Area;

public interface AreaMapper {
    Area selectByPrimaryKey(Integer id);
    /**根据父ID获取地区*/
	List<Area> getAreaByParent(Integer parentid);
	
	String getAreaNameById(Integer id);
}
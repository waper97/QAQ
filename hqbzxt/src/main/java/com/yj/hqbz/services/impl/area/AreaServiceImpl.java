package com.yj.hqbz.services.impl.area;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yj.hqbz.mapper.area.AreaMapper;
import com.yj.hqbz.model.area.Area;
import com.yj.hqbz.services.area.AreaService;
@Service
public class AreaServiceImpl implements AreaService{

	@Resource
	private AreaMapper areaMapper;

	public List<Area> getAreaByParent(Integer parentid) {
		return areaMapper.getAreaByParent(parentid);
	}

	public String getAreaNameById(Integer id) {
		return areaMapper.getAreaNameById(id);
	}
	
	
	
}

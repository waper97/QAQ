package com.yj.hqbz.mapper.system;

import java.util.List;

import com.yj.hqbz.model.system.SystemParams;


public interface SystemParamsMapper {
	/**获取全部系统参数*/
	List<SystemParams> selectAll();
	
	SystemParams selectByCode(String code);
	
	void insert(SystemParams param);
	
	void update(SystemParams param);
	
	void delete(SystemParams param);
	
}
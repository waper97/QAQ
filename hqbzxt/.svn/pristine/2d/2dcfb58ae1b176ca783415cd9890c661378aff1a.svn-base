package com.yj.hqbz.services.system;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.system.SystemParams;



public interface SystemParamService {
	/** 取得所有的系统参数信息*/
	List<SystemParams> listAllParams();
	
	PageInfo<SystemParams> listParams(int page,int pageSize);
	
	SystemParams getParamByCode(String code);
	
	void insertOrUpdateParam(SystemParams param);
	
	void deleteParam(SystemParams param);
	
}

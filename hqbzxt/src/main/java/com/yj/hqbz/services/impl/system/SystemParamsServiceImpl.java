package com.yj.hqbz.services.impl.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.system.SystemParamsMapper;
import com.yj.hqbz.model.system.SystemParams;
import com.yj.hqbz.services.system.SystemParamService;

@Service
public class SystemParamsServiceImpl implements SystemParamService{
	
	@Resource
	private SystemParamsMapper mapper;
	
	public List<SystemParams> listAllParams() {
		return mapper.selectAll();
	}
	
	public PageInfo<SystemParams> listParams(int page,int pageSize){
		PageHelper.startPage(page,pageSize);
		List<SystemParams> list = mapper.selectAll();
		PageInfo<SystemParams> info = new PageInfo<SystemParams>(list);
		return info;
	}
	
	public SystemParams getParamByCode(String code){
		return mapper.selectByCode(code);
	}
	
	@Transactional
	public void insertOrUpdateParam(SystemParams param){
		if(param.getId()!=null){
			mapper.update(param);
		}
		else{
			mapper.insert(param);
		}
	}
	
	@Transactional
	public void deleteParam(SystemParams param){
		mapper.delete(param);
	}

	
}

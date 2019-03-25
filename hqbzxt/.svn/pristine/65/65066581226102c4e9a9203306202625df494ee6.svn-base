package com.yj.hqbz.services.impl.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.system.ErrorLogMapper;
import com.yj.hqbz.mapper.system.OperationLogMapper;
import com.yj.hqbz.model.system.ErrorLog;
import com.yj.hqbz.model.system.OperationLog;
import com.yj.hqbz.services.system.OperationLogService;
@Service
public class OperationLogServiceImpl implements OperationLogService{
	
	@Resource
	private OperationLogMapper logMapper;
	
	@Resource
	ErrorLogMapper errorMapper;
	
	/**添加日志*/
	@Transactional
	public void addLog(OperationLog log) {
		logMapper.addLog(log);
	}
	
	@Transactional
	public void addErrorLog(ErrorLog log) {
		errorMapper.insert(log);
	}
	
	
	/**日志查询*/
	public PageInfo<OperationLog> getLogsByUser(Map<String, Object> map,int page,int rows) {
		PageHelper.startPage(page,rows);
		List<OperationLog> list = logMapper.getLogsByUser(map);
		PageInfo<OperationLog> info=new PageInfo<OperationLog>(list);
		return info;
	}
	
	/**定时删除日志*/
	@Transactional
	public void deleteLogByLessTime(Date time) {
		logMapper.deleteLogByLessTime(time);
		errorMapper.deleteLogByLessTime(time);
	}


}

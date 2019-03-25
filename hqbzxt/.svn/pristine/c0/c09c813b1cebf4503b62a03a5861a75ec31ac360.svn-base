package com.yj.hqbz.services.system;

import java.util.Date;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.system.ErrorLog;
import com.yj.hqbz.model.system.OperationLog;

public interface OperationLogService {
	/**添加日志*/
	void addLog(OperationLog log);
	/**日志查询*/
	PageInfo<OperationLog> getLogsByUser(Map<String,Object> map,int page,int rows);
	/**定时删除日志*/
	void deleteLogByLessTime(Date time);
	
	/**
	 * 增加错误日志
	 * @param log
	 */
	void addErrorLog(ErrorLog log);
}

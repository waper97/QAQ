package com.yj.hqbz.mapper.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.system.OperationLog;

public interface OperationLogMapper {
	/**添加日志*/
	void addLog(OperationLog log);
	/**日志查询*/
	List<OperationLog> getLogsByUser(Map<String,Object> map);
	/**定时删除日志*/
	void deleteLogByLessTime(Date time);
}
package com.yj.hqbz.mapper.system;

import java.util.Date;

import com.yj.hqbz.model.system.ErrorLog;

public interface ErrorLogMapper {
	
    ErrorLog selectByPrimaryKey(Integer id);
    
    void insert(ErrorLog log);
    
    void deleteLogByLessTime(Date time);
}
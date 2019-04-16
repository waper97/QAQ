package com.yj.hqbz.mapper.schoolOperation;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.schoolOperation.AttendanceIndex;

public interface AttendanceIndexMapper {
	
    int deleteByPrimaryKey(AttendanceIndex index);

    int insert(AttendanceIndex record);

    AttendanceIndex selectByPrimaryKey(String indexid);

    int updateByPrimaryKey(AttendanceIndex record);

	List<AttendanceIndex> getList(Map<String, Object> param);

}
package com.yj.hqbz.mapper.schoolOperation;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.schoolOperation.DiningOut;
import org.apache.ibatis.annotations.Param;

public interface DiningOutMapper {

    int insert(DiningOut record);

    int updateByPrimaryKey(DiningOut record);

	List<DiningOut> getListByParam(Map<String, Object> param);

	DiningOut selectByPrimaryKey(@Param("id") String id, @Param("menuCode") String menuCode);

	void deleteById(DiningOut diningOut);
}
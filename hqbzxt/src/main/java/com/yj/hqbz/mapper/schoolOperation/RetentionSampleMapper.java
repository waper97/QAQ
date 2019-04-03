package com.yj.hqbz.mapper.schoolOperation;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.schoolOperation.RetentionSample;

public interface RetentionSampleMapper {
    void deleteByPrimaryKey(RetentionSample sample);

    void insert(RetentionSample record);

    RetentionSample selectByPrimaryKey(String rsid);

    void updateByPrimaryKey(RetentionSample record);

	List<RetentionSample> getListByParem(Map<String, Object> param);

	RetentionSample selectByMenuCode(String menuCode);
}
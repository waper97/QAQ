package com.yj.hqbz.mapper.schoolOperation;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.schoolOperation.RetentionSample;
import com.yj.hqbz.model.schoolOperation.RetentionSampleAttachment;
import org.apache.ibatis.annotations.Param;

public interface RetentionSampleMapper {
    void deleteByPrimaryKey(RetentionSample sample);

    void insert(RetentionSample record);

    RetentionSample selectByPrimaryKey(String rsid);

    void updateByPrimaryKey(RetentionSample record);

	List<RetentionSample> getListByParem(Map<String, Object> param);

	List<RetentionSampleAttachment> getRentionSampleAttachment(String rsid);

	void  insertAttachment(RetentionSampleAttachment attachment);

    void  updateAttachment(RetentionSampleAttachment attachment);

	RetentionSample selectByMenuCode(String menuCode);
    //查询附件
	List<Map<String,Object>> selectAttenchmentByRsId(String rsid);
    //根据留样id删除附件
	void deleteAttachmentByRsid(String rsid);
}
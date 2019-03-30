/**   
* 
*/
package com.yj.hqbz.services.schoolOperation;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.schoolOperation.RetentionSample;
import com.yj.hqbz.model.schoolOperation.RetentionSampleAttachment;
import org.apache.ibatis.annotations.Param;

/**   
 * @Title: RetentionSampleService.java
 * @Package com.yj.hqbz.services.schoolOperation 
 * @Description: TODO
 * @author xx   
 * @date 2019-3-12 
 */
public interface RetentionSampleService {

	PageInfo<RetentionSample> getList(Map<String, Object> param, int page,
			int rows);

	void insert(RetentionSample sample);

	void updateSave(RetentionSample sample);

	RetentionSample selectByPrimaryKey(String rsid);
	//根据留样id查询留样附件
	List<RetentionSampleAttachment> getRentionSampleAttachment(String rsid);

	void deleteByPrimaryKey(RetentionSample sample);

}

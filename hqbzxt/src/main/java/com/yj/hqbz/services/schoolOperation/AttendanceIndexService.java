/**   
* 
*/
package com.yj.hqbz.services.schoolOperation;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.schoolOperation.AttendanceIndex;
import com.yj.hqbz.model.schoolOperation.PracticeUser;

/**   
 * @Title: AttendanceIndexService.java
 * @Package com.yj.hqbz.services.schoolOperation 
 * @Description: TODO
 * @author xx   
 * @date 2019-3-9 
 */
public interface AttendanceIndexService {

	/**
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<AttendanceIndex> getList(Map<String, Object> param, int page, int rows);

	/**
	 * @param attIndex
	 */
	void addSave(AttendanceIndex attIndex);

	/**
	 * @param attIndex
	 */
	void updateSave(AttendanceIndex attIndex);

	/**
	 * @param indexid
	 */
	AttendanceIndex getById(String indexid);

	/**
	 * @param indexid
	 */
	void deleteById(AttendanceIndex index);

}
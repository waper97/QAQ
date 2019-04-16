/**   
* 
*/
package com.yj.hqbz.services.schoolOperation;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.schoolOperation.PracticeUser;

/**   
 * @Title: PracticeUserService.java
 * @Package com.yj.hqbz.services.schoolOperation 
 * @Description: TODO
 * @author xx   
 * @date 2019-3-8 
 */
public interface PracticeUserService {

	/**
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<PracticeUser> getList(Map<String, Object> param, int page, int rows);

	/**
	 * 查询人员列表(不分页)
	 * @param params
	 * @return
	 */
	List<PracticeUser> getListByCondition(Map<String,Object> params);

	/**
	 * @param user
	 */
	void addUser(PracticeUser user);

	/**
	 * @param user
	 */
	void updateUser(PracticeUser user);

	/**
	 * @param userid
	 * @return
	 */
	PracticeUser getPeople(String userid);

	/**
	 * @param userid
	 */
	void deletePeople(PracticeUser people);

}
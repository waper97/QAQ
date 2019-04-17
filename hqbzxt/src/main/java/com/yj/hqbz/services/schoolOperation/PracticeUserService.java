/**   
* 
*/
package com.yj.hqbz.services.schoolOperation;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.schoolOperation.HealthAndAgeWaring;
import com.yj.hqbz.model.schoolOperation.PracticeUser;
import com.yj.hqbz.model.schoolOperation.Staff;

import java.util.List;
import java.util.Map;

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

	/**
	 * 统计人员总数以及各个职位的人数
	 * @param orgid 组织id（食堂id）
	 * @return
	 */
	Staff getPersonnelStatisticsByOrgId(String orgid);

	HealthAndAgeWaring getHealthAndAgeWarningByorgid(String orgid);

}

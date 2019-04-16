/**   
* 
*/
package com.yj.hqbz.services.impl.schoolOperation;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.schoolOperation.PracticeUserMapper;
import com.yj.hqbz.model.schoolOperation.PracticeUser;
import com.yj.hqbz.services.schoolOperation.PracticeUserService;
import com.yj.hqbz.util.CommUtil;
import com.yj.hqbz.util.DateUtil;

/**   
 * @Title: PracticeUserServiceImpl.java
 * @Package com.yj.hqbz.services.impl.schoolOperation 
 * @Description: TODO
 * @author xx   
 * @date 2019-3-8 
 */
@Service
public class PracticeUserServiceImpl implements PracticeUserService {
	@Resource
	PracticeUserMapper practiceUserMapper;
	
	
	public PageInfo<PracticeUser> getList(Map<String, Object> param, int page,
			int rows) {
		PageHelper.startPage(page,rows);
		List<PracticeUser> list=practiceUserMapper.getList(param);
		PageInfo<PracticeUser> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	
	public List<PracticeUser> getListByCondition(Map<String, Object> params) {
		List<PracticeUser> list = practiceUserMapper.getList(params);
		return list;
	}

	
	@Transactional
	public void addUser(PracticeUser user) {
		practiceUserMapper.insert(user);
	}

	
	@Transactional
	public void updateUser(PracticeUser user) {
		practiceUserMapper.updateByPrimaryKey(user);
	}

	
	public PracticeUser getPeople(String userid) {
		return practiceUserMapper.selectByPrimaryKey(userid);
	}

	
	@Transactional
	public void deletePeople(PracticeUser people) {
		practiceUserMapper.deleteByPrimaryKey(people);
	}



}
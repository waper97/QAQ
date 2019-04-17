package com.yj.hqbz.mapper.schoolOperation;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.schoolOperation.HealthAndAgeWaring;
import com.yj.hqbz.model.schoolOperation.PracticeUser;
import com.yj.hqbz.model.schoolOperation.Staff;
import org.apache.ibatis.annotations.Param;

public interface PracticeUserMapper {
	
    int deleteByPrimaryKey(PracticeUser people);

    int insert(PracticeUser record);

    PracticeUser selectByPrimaryKey(String userid);

    int updateByPrimaryKey(PracticeUser record);

	List<PracticeUser> getList(Map<String, Object> param);
    //统计人员总数以及各个职位的人数
    Staff getPersonnelStatisticsByOrgId(@Param("orgid") String orgid);
    //统计健康证、年龄到期预警的人数
    HealthAndAgeWaring getHealthAndAgeWarningByorgid(@Param("orgid")String orgid);
}
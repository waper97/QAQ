package com.yj.hqbz.mapper.org;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.org.Organization;

public interface OrganizationMapper {
	/**根据主键获取机构*/
    Organization getByPrimaryKey(Integer orgid);
    /**根据机构分类获取机构（判断分类下是否包含机构）*/
	List<Organization> getOrgByOrgType(Integer orgTypeid);
	/**获取机构列表*/
	List<Organization> getOrgBill(Map<String,Object> param);
	/**获取机构列表（简单信息，包括ID，名称）**/
	List<Organization> getOrgSimpleBill(Map<String,Object> param);
	
	/**根据ID获取机构(含外键名称)*/
	Organization getOrgById(Integer orgid); 
	/**根据名称获取机构*/
	Organization getOrgByNameAndStyle(Organization org);
	/**新增机构*/
	int addOrg(Organization org);
	/**修改机构*/
	int updateOrg(Organization org);
	/**修改机构状态*/
	int updateStatus(Organization org);
}
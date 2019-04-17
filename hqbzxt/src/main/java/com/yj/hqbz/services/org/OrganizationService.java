package com.yj.hqbz.services.org;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.org.CustomerBanner;
import com.yj.hqbz.model.org.Organization;

public interface OrganizationService {
	/**根据主键获取机构*/
	Organization getByPrimaryKey(Integer orgid); 
	/**根据机构分类获取机构（判断分类下是否包含机构）*/
	List<Organization> getOrgByOrgType(Integer orgTypeid);
	/**获取机构列表*/
	PageInfo<Organization> getOrgBill(Map<String,Object> param,int page,int rows);
	/**获取启用的机构列表（简单信息，只包括ID，机构名称和机构编码）**/
	PageInfo<Organization> getOrgSimpleBill(Map<String, Object> param, int page, int rows);
	/**根据ID获取机构(含外键名称)*/
	Organization getOrgById(Integer orgid); 
	/**根据ID获取机构Banner*/
	List<CustomerBanner> getOrgBanner(Integer orgid); 
	/**根据名称获取机构*/
	Organization getOrgByNameAndStyle(Organization org);
	/**新增机构*/
	int addOrg(Organization org);
	/**修改机构*/
	int updateOrg(Organization org);
	/**修改机构状态*/
	int updateStatus(Organization org);
	/**判断机构下是否有用户*/
	boolean haveUserByOrg(Integer orgid);
}

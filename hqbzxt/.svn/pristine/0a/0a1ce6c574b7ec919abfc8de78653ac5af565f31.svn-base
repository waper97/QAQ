package com.yj.hqbz.services.org;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.org.OrgType;

public interface OrgTypeService {
	/**根据主键获取机构分类*/
    OrgType getByPrimaryKey(Integer id);
	/**获取机构分类列表*/
	PageInfo<OrgType> getOrgTypeBill(Map<String,Object> param,int page,int rows);
	/**获取机构分类树形列表*/
	List<OrgType> getOrgTypeTree(Map<String,Object> param,Integer isParent);
	/**根据父ID获取机构分类*/
	List<OrgType> getOrgTypeByParent(Integer parentid);
	/**根据类型和名字获取机构分类*/
	OrgType getOrgTypeByNameAndType(OrgType orgType);
	/**添加机构分类*/
	int addOrgType(OrgType orgType);
	/**修改机构分类*/
	int updateOrgType(OrgType orgType);
	/**修改机构分类状态*/
	int updateStatus(OrgType orgType);
}

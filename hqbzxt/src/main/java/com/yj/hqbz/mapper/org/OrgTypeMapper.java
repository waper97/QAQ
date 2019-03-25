package com.yj.hqbz.mapper.org;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.org.OrgType;

public interface OrgTypeMapper {
	/**根据主键获取机构类型*/
    OrgType getByPrimaryKey(Integer id);
    /**获取机构分类列表*/
	List<OrgType> getOrgTypeBill(Map<String,Object> param);
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
	/**根据父机构停用机构分类*/
	int updateStatusByParent(OrgType orgType);
}
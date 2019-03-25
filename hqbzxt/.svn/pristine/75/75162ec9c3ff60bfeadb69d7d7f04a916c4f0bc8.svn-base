package com.yj.hqbz.services.impl.org;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.org.OrgTypeMapper;
import com.yj.hqbz.model.org.OrgType;
import com.yj.hqbz.services.org.OrgTypeService;
@Service
public class OrgTypeServiceImpl implements OrgTypeService{
	
	@Resource
	private OrgTypeMapper orgTypeMapper; 

	public OrgType getByPrimaryKey(Integer id) {
		return orgTypeMapper.getByPrimaryKey(id);
	}
	
	public PageInfo<OrgType> getOrgTypeBill(Map<String, Object> param, int page, int rows) {
		PageHelper.startPage(page, rows);
		List<OrgType> list = orgTypeMapper.getOrgTypeBill(param);
		PageInfo<OrgType> info=new PageInfo<OrgType>(list);
		return info;
	}

	public List<OrgType> getOrgTypeTree(Map<String, Object> param, Integer isParent) {
		//parentid=-1表示获取父分类
		param.put("parentid", -1);
		List<OrgType> parentList =orgTypeMapper.getOrgTypeBill(param);
		if(isParent==null||isParent!=1) {
			getChildren(parentList, param);
		}
		return parentList;
	}
	
	private void getChildren(List<OrgType> parentList,Map<String, Object> param){
		if(parentList!=null&&parentList.size()>0) {
			for (OrgType orgType : parentList) {
				param.put("parentid", orgType.getId());
				List<OrgType> orgTypeBill = orgTypeMapper.getOrgTypeBill(param);
				getChildren(orgTypeBill, param);
				orgType.setChildren(orgTypeBill);
			}
		}
	}
	
	public List<OrgType> getOrgTypeByParent(Integer parentid) {
		return orgTypeMapper.getOrgTypeByParent(parentid);
	}
	
	public OrgType getOrgTypeByNameAndType(OrgType orgType) {
		return orgTypeMapper.getOrgTypeByNameAndType(orgType);
	}
	
	@Transactional
	public int addOrgType(OrgType orgType) {
		return orgTypeMapper.addOrgType(orgType);
	}

	@Transactional
	public int updateOrgType(OrgType orgType) {
		return orgTypeMapper.updateOrgType(orgType);
	}
	
	@Transactional
	public int updateStatus(OrgType orgType) {
		int i = orgTypeMapper.updateStatus(orgType);
		//停用下级机构分类
		if(i>0&&orgType.getStatus()==1) {
			orgTypeMapper.updateStatusByParent(orgType);
		}
		return i;
	}
	
}

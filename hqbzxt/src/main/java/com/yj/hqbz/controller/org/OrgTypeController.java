package com.yj.hqbz.controller.org;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.hqbz.model.org.OrgType;
import com.yj.hqbz.model.org.Organization;
import com.yj.hqbz.services.org.OrgTypeService;
import com.yj.hqbz.services.org.OrganizationService;
import com.yj.hqbz.util.StringUtil;
@RestController
public class OrgTypeController extends BaseController{

	@Resource
	private OrgTypeService orgTypeService;
	@Resource
	private OrganizationService orgService;
	
	@RequestMapping("/org/manage/addOrgType")
	public Object addOrgType(OrgType orgType) {
		if(orgType.getType()==null) {
			return fail("机构分类类型不能为空！");
		}
		if(StringUtil.isBlank(orgType.getName())) {
			return fail("机构分类名称不能为空！");
		}
		OrgType oldOrgType = orgTypeService.getOrgTypeByNameAndType(orgType);
		if(oldOrgType!=null) {
			return fail("机构分类名称已存在,请重命名！");
		}
		//状态：0-启用，1-停用
		orgType.setStatus(0);
		orgType.setCreator(getTokenUser().getTrueName());
		orgType.setCreateDate(new Date());
		orgType.setLastOpDate(orgType.getCreateDate());
		orgType.setLastOpUser(orgType.getCreator());
		orgTypeService.addOrgType(orgType);
		//日志
		saveJournalLog("新增【"+getOrgTypeStr(orgType.getType())+"】机构分类【"+orgType.getName()+"】", "id:"+orgType.getId());
		return success("添加成功！");
	}
	
	@RequestMapping("/org/manage/updateOrgType")
	public Object updateOrgType(OrgType orgType) {
		if(orgType.getId()==null) {
			return fail("机构分类ID不能为空！");
		}
		if(StringUtil.isBlank(orgType.getName())) {
			return fail("机构分类名称不能为空！");
		}
		OrgType oldOrgType = orgTypeService.getByPrimaryKey(orgType.getId());
		if(oldOrgType==null) {
			return fail("机构分类不存在！");
		}
		if(oldOrgType.getStatus()==2) {
			return fail("机构分类已删除，不能修改！");
		}
		//设置类型
		orgType.setType(oldOrgType.getType());
		OrgType oldOrgType2 = orgTypeService.getOrgTypeByNameAndType(orgType);
		if(oldOrgType2!=null&&oldOrgType2.getId().intValue()!=orgType.getId().intValue()) {
			return fail("机构分类名称已存在,请重命名！");
		}
		//设置修改人
		orgType.setLastOpUser(getTokenUser().getTrueName());
		orgType.setLastOpDate(new Date());
		orgTypeService.updateOrgType(orgType);
		//日志
		saveJournalLog("修改【"+getOrgTypeStr(orgType.getType())+"】机构分类【"+orgType.getName()+"】", "id:"+orgType.getId());
		return success("修改成功！");
	}
	
	@RequestMapping("/org/manage/disableOrgType")
	public Object disableOrgType(Integer id,Integer status) {
		if(id==null) {
			return fail("机构分类ID不能为空！");
		}
		if(status==null) {
			return fail("机构分类状态不能为空！");
		}
		//状态：0-正常，1-停用
		String str=status==0?"启用":"停用";
		OrgType orgType = orgTypeService.getByPrimaryKey(id);
		if(orgType==null) {
			return fail("机构分类不存在！");
		}
		if(orgType.getStatus()==2) {
			return fail("机构分类已删除，不允许"+str+"！");
		}
		if(orgType.getStatus().intValue()==status.intValue()) {
			return fail("机构分类已"+str+",不允许重复操作！");
		}
		orgType.setStatus(status);
		orgType.setLastOpDate(new Date());
		orgType.setLastOpUser(getTokenUser().getTrueName());
		orgTypeService.updateStatus(orgType);
		//日志
		saveJournalLog(str+"【"+getOrgTypeStr(orgType.getType())+"】机构分类【"+orgType.getName()+"】", "id:"+id);
		return success(str+"成功！");
	}
	
	@RequestMapping("/org/manage/deleteOrgType")
	public Object deleteOrgType(Integer id) {
		if(id==null) {
			return fail("机构分类ID不能为空！");
		}
		OrgType orgType = orgTypeService.getByPrimaryKey(id);
		if(orgType==null) {
			return fail("机构分类不存在！");
		}
		if(orgType.getStatus()==0) {
			return fail("机构分类已启用，不允许删除！");
		}
		if(orgType.getStatus()==2) {
			return fail("机构分类已删除，不允许重复操作！");
		}
		List<OrgType> children = orgTypeService.getOrgTypeByParent(id);
		if(children!=null&&children.size()>0) {
			return fail("该机构分类下含有下级机构分类，不允许删除！");
		}
		List<Organization> orgList = orgService.getOrgByOrgType(id);
		if(orgList!=null&&orgList.size()>0) {
			return fail("该机构分类下含有机构，不允许删除！");
		}
		orgType.setStatus(2);
		orgType.setLastOpDate(new Date());
		orgType.setLastOpUser(getTokenUser().getTrueName());
		orgTypeService.updateStatus(orgType);
		//日志
		saveJournalLog("删除【"+getOrgTypeStr(orgType.getType())+"】机构分类【"+orgType.getName()+"】", "id:"+id);
		return success("删除成功！");
	}
	
	@RequestMapping("/org/common/getOrgTypeBill")
	public Object getOrgTypeBill(Integer type,String code,String name,Integer status,Integer parentid,DataGridModel model) {
		if(type==null) {
			return fail("机构分类类型不能为空！");
		}
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("type", type);
		param.put("code", code);
		param.put("name", name);
		param.put("status", status);
		param.put("parentid", parentid);
		param.put("orderBy", model.getOrderBy());
		param.put("orderType", model.getOrderType());
		PageInfo<OrgType> info = orgTypeService.getOrgTypeBill(param, model.getPage(), model.getRows());
		return new BaseRes("获取成功！", info.getTotal(), info.getPages(), info.getList());
	}
	
	@RequestMapping("/org/common/getOrgTypeDetail")
	public Object getOrgTypeDetail(Integer id) {
		OrgType orgType = orgTypeService.getByPrimaryKey(id);
		if(orgType==null) {
			return fail("机构分类不存在！");
		}else {
			return success(orgType);
		}
	}
	
	@RequestMapping("/org/common/getOrgTypeTree")
	public Object getOrgTypeTree(Integer type,Integer status,Integer isParent) {
		if(type==null) {
			return fail("机构分类类型不能为空！");
		}
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("type", type);
		param.put("status", status);
		List<OrgType> list = orgTypeService.getOrgTypeTree(param,isParent);
		return success(list);
	}
	
	//获取机构类型字符串
	private String getOrgTypeStr(Integer type) {
		if(type!=null&&type==0) {
			return "供应商";
		}else if(type!=null&&type==1){
			return "商家";
		}else if(type!=null&&type==2){
			return "客户";
		}else if(type!=null&&type==3) {
			return "食药监";
		}else {
			return null;
		}
	}
}

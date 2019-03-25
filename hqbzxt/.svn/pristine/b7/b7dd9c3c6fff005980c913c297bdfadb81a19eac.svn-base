package com.yj.hqbz.controller.org;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.common.model.YJOrgDB;
import com.yj.common.tools.YJOrgDBUtil;
import com.yj.hqbz.model.goods.OrgSku;
import com.yj.hqbz.model.org.Organization;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.goods.OrgSkuService;
import com.yj.hqbz.services.org.OrganizationService;
import com.yj.hqbz.util.DateUtil;
import com.yj.hqbz.util.StringUtil;
@RestController
public class OrganizationController extends BaseController{
	@Resource
	private OrganizationService orgService;
	@Resource
	private OrgSkuService orgSkuService;
	
	@RequestMapping("/org/manage/addOrg")
	public Object addOrg(@RequestBody Organization org) {
		if(StringUtil.isBlank(org.getName())) {
			return fail("机构名称不能为空！");
		}
		if(org.getOrgStyle()==null) {
			return fail("机构类型不能为空！");
		}
		Organization oldOrg = orgService.getOrgByNameAndStyle(org);
		if(oldOrg!=null) {
			return fail("机构名称已存在,请重命名！");
		}
		org.setNamepy(getPinYin(null, org.getName()));
		org.setStatus(0);
		org.setCreator(getTokenUser().getTrueName());
		org.setCreateDate(new Date());
		org.setLastOpDate(org.getCreateDate());
		org.setLastOpUser(org.getCreator());
		orgService.addOrg(org);
		//日志
		saveJournalLog("新增【"+getOrgStyleStr(org.getOrgStyle())+"】机构【"+org.getName()+"】", "id:"+org.getOrgid());
		return success("添加成功！");
	}
	
	@RequestMapping("/org/manage/updateOrg")
	public Object updateOrg(@RequestBody Organization org) {
		if(org.getOrgid()==null) {
			return fail("机构ID不能为空！");
		}
		if(StringUtil.isBlank(org.getName())) {
			return fail("机构名称不能为空！");
		}
		Organization oldOrg = orgService.getByPrimaryKey(org.getOrgid());
		if(oldOrg==null) {
			return fail("机构不存在！");
		}
		if(oldOrg.getStatus()==2) {
			return fail("机构已删除，不能修改！");
		}
		//设置类型
		org.setOrgStyle(oldOrg.getOrgStyle());
		Organization oldOrg2 = orgService.getOrgByNameAndStyle(org);
		if(oldOrg2!=null&&oldOrg2.getOrgid().intValue()!=org.getOrgid().intValue()) {
			return fail("机构名称已存在,请重命名！");
		}
		//设置拼音和修改人
		org.setNamepy(getPinYin(null, org.getName()));
		org.setLastOpUser(getTokenUser().getTrueName());
		org.setLastOpDate(new Date());
		orgService.updateOrg(org);
		//日志
		saveJournalLog("修改【"+getOrgStyleStr(org.getOrgStyle())+"】机构【"+org.getName()+"】", "id:"+org.getOrgid());
		return success("修改成功！");
	}
	
	@RequestMapping("/org/manage/disableOrg")
	public Object disableOrg(Integer orgid,Integer status) {
		if(orgid==null) {
			return fail("机构ID不能为空！");
		}
		if(status==null) {
			return fail("机构状态不能为空！");
		}
		//状态：0-正常，1-停用
		String str=status==0?"启用":"停用";
		Organization org = orgService.getByPrimaryKey(orgid);
		if(org==null) {
			return fail("机构不存在！");
		}
		if(org.getStatus()==2) {
			return fail("机构已删除，不允许"+str+"！");
		}
		if(org.getStatus().intValue()==status.intValue()) {
			return fail("机构已"+str+",不允许重复操作！");
		}
		org.setStatus(status);
		org.setLastOpUser(getTokenUser().getTrueName());
		org.setLastOpDate(new Date());
		orgService.updateStatus(org);
		//日志
		saveJournalLog(str+"【"+getOrgStyleStr(org.getOrgStyle())+"】机构【"+org.getName()+"】", "id:"+orgid);
		return success(str+"成功！");
	}
	
	@RequestMapping("/org/manage/deleteOrg")
	public Object deleteOrg(Integer orgid) {
		if(orgid==null) {
			return fail("机构ID不能为空！");
		}
		Organization org = orgService.getByPrimaryKey(orgid);
		if(org==null) {
			return fail("机构不存在！");
		}
		if(org.getStatus()==0) {
			return fail("机构已启用，不允许删除！");
		}
		if(org.getStatus()==2) {
			return fail("机构已删除，不允许重复操作！");
		}
		
		PageInfo<OrgSku> orgSkuPage = orgSkuService.getOrgSkuByOrg(orgid,1,1);
		if(orgSkuPage.getTotal()>0) {
			return fail("该机构下有商品，不允许删除！");
		}
		org.setStatus(2);
		org.setLastOpUser(getTokenUser().getTrueName());
		org.setLastOpDate(new Date());
		orgService.updateStatus(org);
		//日志
		saveJournalLog("删除【"+getOrgStyleStr(org.getOrgStyle())+"】机构【"+org.getName()+"】", "id:"+orgid);
		return success("删除成功！");
	}
	
	@RequestMapping("/org/common/getOrgList")
	public Object getOrgBill(Integer orgStyle,String code,String name,Integer orgType,String scopeType,Integer status,String area,String phone,String beginTime,String endTime,DataGridModel model) {
		Date beginDate=null;
		Date endDate=null;
		if(StringUtil.isNotBlank(beginTime)) {
			beginDate=DateUtil.getDateByStr(beginTime.trim()+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
		}
		if(StringUtil.isNotBlank(endTime)) {
			endDate=DateUtil.getDateByStr(endTime.trim()+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		}
		if(beginDate!=null&&endDate!=null&&beginDate.after(endDate)) {
			return fail("开始时间不能小于结束时间！");
		}
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("beginDate", beginDate);
		param.put("endDate", endDate);
		param.put("orgStyle", orgStyle);
		param.put("code", code);
		param.put("name", name);
		param.put("orgType", orgType);
		param.put("scopeType", scopeType);
		param.put("status", status);
		param.put("area", area);
		param.put("phone", phone);
		param.put("orderBy", model.getOrderBy());
		param.put("orderType", model.getOrderType());
		PageInfo<Organization> info = orgService.getOrgBill(param, model.getPage(), model.getRows());
		return new BaseRes("获取成功！", info.getTotal(), info.getPages(), info.getList());
	}
	
	@RequestMapping("/org/common/getOrgDetail")
	public Object getOrgDetail(Integer orgid) {
		Organization org=orgService.getOrgById(orgid);
		if(org==null) {
			return fail("机构不存在！");
		}else {
			return success(org);
		}
	}
	
	@RequestMapping("/org/common/getSchoolList")
	public Object getSchoolBill(String name,DataGridModel model) {
		//组装参数
		Map<String,Object> paramMap=new HashMap<String, Object>();
		if(StringUtil.isNotBlank(name)) {
			paramMap.put("name", name);
		}
		paramMap.put("page", model.getPage());
		paramMap.put("rows", model.getRows());
		YJOrgDB db = YJOrgDBUtil.getBaseUserDB(JSON.toJSONString(paramMap), "/api/org/getSchoolNameBill");
		if(db!=null) {
			if(db.getSuccess()) {
				return success(db.getData());
			}else {
				return fail(db.getMsg());
			}
		}else {
			return fail("获取失败！");
		}
	}
	
	
	/**
	 * 根据用户获取该用户访问的机构列表
	 * @return
	 */
	@RequestMapping("/org/common/getSchoolOrgList")
	public Object getSchoolOrgList(){
		UserInfo user = getTokenUser();
		if(user.getUserRole().intValue()==1){
			return fail("操作非法！");
		}
		else{
			String orgId=user.getOrgId().toString();
			if(user.getOrgManager().intValue()==1){
				Organization org = orgService.getOrgById(user.getOrgId());
				orgId = org.getBelong().getOrgids();
			}
			if(StringUtil.isBlank(orgId)){
				return fail("操作非法！");
			}
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("orgIdArr", orgId.split(","));
			return success(orgService.getOrgBill(param, 1, 0).getList());
		}
	}
	
	/**
	 * 获取当前用户所在的机构
	 * @return
	 */
	@RequestMapping("/org/common/getUserOrgInfo")
	public Object getOrganizationOrgInfo(){
		UserInfo user = getTokenUser();
		if(user.getUserRole()==0){
			return fail("没有信息可获取");
		}
		else{
			return success(orgService.getOrgById(user.getOrgId()));
		}
	}
	
	
	//获取机构类型字符串
	private String getOrgStyleStr(Integer style) {
		if(style!=null&&style==0) {
			return "供应商";
		}else if(style!=null&&style==1){
			return "商家";
		}else if(style!=null&&style==2){
			return "客户";
		}else if(style!=null&&style==3) {
			return "食药监";
		}else {
			return null;
		}
	}
}

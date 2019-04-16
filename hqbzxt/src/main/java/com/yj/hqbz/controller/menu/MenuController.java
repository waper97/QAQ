package com.yj.hqbz.controller.menu;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.hqbz.model.menu.MenuIndex;
//import com.yj.hqbz.model.menu.MenuIndex;
import com.yj.hqbz.services.menu.MenuService;
import com.yj.hqbz.util.DateUtil;
import com.yj.hqbz.util.StringUtil;

@RestController
@RequestMapping("/menu/school")
public class MenuController extends BaseController{

	@Resource
	private MenuService menuService;
	
	@RequestMapping("/getList")
	public Object getList(Integer menuType,String mealsBeginDate,String mealsEndDate,Integer status,Integer showDraft,String creator,Integer orgid,DataGridModel model) {
		Date beginDate=null;
		Date endDate=null;
		if(StringUtil.isNotBlank(mealsBeginDate)) {
			beginDate=DateUtil.getDateByStr(mealsBeginDate.trim()+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
		}
		if(StringUtil.isNotBlank(mealsEndDate)) {
			endDate=DateUtil.getDateByStr(mealsEndDate.trim()+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		}
		if(beginDate!=null&&endDate!=null&&beginDate.after(endDate)) {
			return fail("开始时间不能小于结束时间！");
		}
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("menuType", menuType);
		param.put("beginDate", beginDate);
		param.put("endDate", endDate);
		param.put("status", status);
		param.put("showDraft", showDraft);
		param.put("creator", creator);
		param.put("orgid", orgid);
		PageInfo<MenuIndex> info = menuService.getMenuList(param, model.getPage(), model.getRows());
		return new BaseRes("获取成功！", info.getTotal(), info.getPages(), info.getList()); 
	}
	
	@RequestMapping("/addSave")
	public Object addSave(@RequestBody MenuIndex menu) {
		if(menu.getMenuType()==null||menu.getMealsDate()==null||menu.getCount()==null||menu.getStatus()==null) {
			return fail("餐食类别、日期、用餐人数、提交状态不能为空！");
		}
		if(StringUtil.isBlank(menu.getCheckerid())||StringUtil.isBlank(menu.getCheckName())) {
			return fail("审核人不能为空！");
		}
		if(menu.getStatus()==1&&(menu.getDetail()==null||menu.getDetail().size()<=0)) {
			return fail("菜名和食材不能为空！");
		}
		menu.setMenuid(StringUtil.getUUID());
		menu.setOrgid(getTokenUser().getOrgId());
		menu.setMenuCode(DateUtil.getStrByDate(new Date(), "yyyyMMdd"));
		menu.setDeleteStatus(0);
		menu.setCreator(getTokenUser().getTrueName());
		menu.setCreateDate(new Date());
		menu.setLastOpUser(menu.getCreator());
		menu.setLastOpDate(menu.getCreateDate());
		menuService.addSave(menu);
		//日志
		saveJournalLog("新增菜单", "menuid:"+menu.getMenuid());
		return success("添加成功！");
	}
	
	@RequestMapping("/updateSave")
	public Object updateSave(@RequestBody MenuIndex menu) {
		if(StringUtil.isBlank(menu.getMenuid())||menu.getMenuType()==null||menu.getMealsDate()==null||menu.getCount()==null||menu.getStatus()==null) {
			return fail("菜单ID、餐食类别、日期、用餐人数、提交状态不能为空！");
		}
		if(StringUtil.isBlank(menu.getCheckerid())||StringUtil.isBlank(menu.getCheckName())) {
			return fail("审核人不能为空！");
		}
		if(menu.getStatus()==1&&(menu.getDetail()==null||menu.getDetail().size()<=0)) {
			return fail("菜名和食材不能为空！");
		}
		MenuIndex oldMenu = menuService.selectByPrimaryKey(menu.getMenuid());
		if(oldMenu==null) {
			return fail("菜单不存在！");
		}
		if(oldMenu.getStatus()!=0) {
			return fail("非草稿状态菜单，不允许修改！");
		}
		if(oldMenu.getDeleteStatus()==1) {
			return fail("菜单已删除，无法修改！");
		}
		menu.setLastOpUser(getTokenUser().getTrueName());
		menu.setLastOpDate(new Date());
		menuService.updateSave(menu);
		//日志
		saveJournalLog("修改菜单", "menuid:"+menu.getMenuid());
		return success("修改成功！");
	}
	
	@RequestMapping("/getMenuInfo")
	public Object getMenuInfo(String menuid) {
		if(StringUtil.isBlank(menuid)) {
			return fail("菜单ID不能为空！");
		}
		MenuIndex menu=menuService.getMenuInfo(menuid);
		if(menu==null) {
			return fail("菜单不存在！");
		}else {
			return success(menu);
		}
	}
	
	@RequestMapping("/checkBill")
	public Object checkBill(String menuid,Integer checkStatus,String checkOpinion) {
		if(StringUtil.isBlank(menuid)) {
			return fail("菜单ID不能为空！");
		}
		MenuIndex menu = menuService.selectByPrimaryKey(menuid);
		if(menu==null) {
			return fail("菜单不存在！");
		}
		if(menu.getDeleteStatus()==1) {
			return fail("菜单已删除，无法审核！");
		}
		if(menu.getStatus()==0) {
			return fail("菜单为草稿状态，不允许审核！");
		}
		if(menu.getStatus()!=1) {
			return fail("菜单已审核，不允许重复操作！");
		}
		if(!menu.getCheckerid().equals(getTokenUserid())) {
			return fail("非指定审核人，不允许审核！");
		}
		if(checkStatus==1) {
			menu.setStatus(2);
		}else if(checkStatus==2) {
			menu.setStatus(3);
		}else {
			return fail("审核状态错误！");
		}
	
		menu.setCheckDate(new Date());
		menu.setCheckOpinion(checkOpinion);
		menu.setLastOpUser(menu.getCheckName());
		menu.setLastOpDate(menu.getCheckDate());
		menu.setCheckerid(this.getTokenUser().getUserid());
		menu.setCheckName(this.getTokenUser().getUserName());
		menuService.checkBill(menu);
		//日志
		saveJournalLog("审核菜单", "menuid:"+menu.getMenuid());
		return success("审核成功！");
		
	}
	
	@RequestMapping("/deleteBill")
	public Object deleteBill(String menuid) {
		if(StringUtil.isBlank(menuid)) {
			return fail("菜单ID不能为空！");
		}
		MenuIndex menu = menuService.selectByPrimaryKey(menuid);
		if(menu==null) {
			return fail("菜单不存在！");
		}
		if(menu.getStatus()!=0) {
			return fail("非草稿状态菜单，不允许删除！");
		}
		if(menu.getDeleteStatus()==1) {
			return fail("菜单已删除，不允许重复操作！");
		}
		
		menu.setDeleteStatus(1);
		menu.setLastOpUser(getTokenUser().getTrueName());
		menu.setLastOpDate(new Date());
		menuService.deleteBill(menu);
		//日志
		saveJournalLog("删除菜单", "menuid:"+menu.getMenuid());
		return success("删除成功！");
	}
	
}
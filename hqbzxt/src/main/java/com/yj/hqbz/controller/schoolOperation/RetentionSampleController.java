/**   
* 
*/
package com.yj.hqbz.controller.schoolOperation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javassist.expr.NewArray;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.hqbz.model.schoolOperation.RetentionSample;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.schoolOperation.RetentionSampleService;
import com.yj.hqbz.util.DateUtil;
import com.yj.hqbz.util.StringUtil;
import com.yj.hqbz.util.UUIDUtil;

/**   
 * @Title: RetationSampleController.java
 * @Package com.yj.hqbz.controller.schoolOperation 
 * @Description: TODO
 * @author xx   
 * @date 2019-3-12 
 */
@RestController
public class RetentionSampleController extends BaseController {
	@Resource
	RetentionSampleService retentionSampleService;
	
	/**
	 * 查看留样列表
	 */
	@GetMapping("/sample/school/getList")
	public Object getList(Integer menuType,String beginDate,
			String endDate,String rsUser,String orgid,DataGridModel model){
		Date beginTime=null;
		Date endTime=null;
		if(StringUtil.isNotBlank(beginDate)) {
			beginTime=DateUtil.getDateByStr(beginDate.trim()+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
		}
		if(StringUtil.isNotBlank(endDate)) {
			endTime=DateUtil.getDateByStr(endDate.trim()+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		}
		if(beginTime!=null&&endTime!=null&&beginTime.after(endTime)) {
			return fail("开始时间不能大于结束时间！");
		}
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("beginDate", beginTime);
		param.put("endDate", endTime);
		param.put("orderBy", model.getOrderBy());
		param.put("orderType", model.getOrderType());
		
		if(orgid != null){
			param.put("orgid", orgid);
		}else{
			//获取当前登录用户,如果为食堂级用户，则设置orgid为对应的食堂，否则为校级，如果有则查询对应orgid，没有则查询所有。
			UserInfo user = this.getTokenUser();
			Integer orgId = user.getOrgId();
			if(user.getUserRole().intValue()==2){//角色是食堂
				Integer isManager = user.getOrgManager();//是否是食堂管理员
				if(isManager==1){
					//是食堂管理员，获取学校机构id,再获取学校所有食堂机构id,sql处理
					param.put("isManager", 1);
					param.put("orgid", orgId);
				}else if(isManager==0){
					//不是食堂管理员,获取用户所在食堂机构的对应人员
					param.put("orgid", orgId);
				}else{
					return fail("食堂管理员参数非法，查询失败！");
				}
			}else{
				return fail("非食堂用户，查询失败！");
			}
		}
		
		param.put("menuType", menuType);
		param.put("rsUser", rsUser);
		PageInfo<RetentionSample> pageInfo=retentionSampleService.getList(param,model.getPage(),model.getRows());
		
		return new BaseRes("OK",pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
	}
	
	/**
	 * 新增留样保存
	 */
	@PostMapping(value = "/sample/school/addSave")
	public Object addSave(@RequestBody RetentionSample sample){
		UserInfo user = this.getTokenUser();
		sample.setCreator(user.getTrueName());
		sample.setCreateDate(new Date());
		if(user.getOrgId()!=null){
			sample.setOrgid(user.getOrgId());
		}else{
			return fail("用户机构id为空，新增失败！");
		}
		if(sample.getDishName()==null||sample.getRsDate()==null||sample.getMenuType()==null||
				sample.getCookerName()==null||sample.getAttachmentList().isEmpty()||
				sample.getQty()==null||sample.getRsUser()==null||sample.getTemperature()==null||
				sample.getRsTime()==null||sample.getRsEndTime()==null||sample.getConductor()==null){
			return fail("必选数据为空，新增失败！");
		}
		if(sample.getRsTime().after(sample.getRsEndTime())){
			return fail("留样时间不能大于到期时间，新增失败！");
		}
		sample.setRsid(UUIDUtil.getUUID());
		retentionSampleService.insert(sample);
		return success("新增成功！");
	}
	
	/**
	 * 修改留样保存
	 */
	@PostMapping("/sample/school/updateSave")
	public Object updateSave(@RequestBody RetentionSample sample){
		if(StringUtil.isBlank(sample.getRsid())){
			return fail("留样id为空，修改失败！");
		}
		if(sample.getDishName()==null||sample.getRsDate()==null||sample.getMenuType()==null||
				sample.getCookerName()==null||sample.getAttachmentList().isEmpty()||
				sample.getQty()==null||sample.getRsUser()==null||sample.getTemperature()==null||
				sample.getRsTime()==null||sample.getRsEndTime()==null||sample.getConductor()==null){
			return fail("必选数据为空，修改失败！");
		}
		if(sample.getRsTime().after(sample.getRsEndTime())){
			return fail("留样时间不能大于到期时间，修改失败！");
		}
		UserInfo user = this.getTokenUser();
		sample.setLastOpUser(user.getTrueName());
		sample.setLastOpDate(new Date());
		retentionSampleService.updateSave(sample);
		return success("修改成功！");
	}
	
	/**
	 * 查看留样详情
	 */
	@GetMapping("/sample/school/getSample")
	public Object getSample(String rsid){
		if(StringUtil.isBlank(rsid)){
			return fail("留样id为空，查询失败！");
		}
		RetentionSample sample=retentionSampleService.selectByPrimaryKey(rsid);
		//查询留样附件（图片）
		sample.setAttachmentList(retentionSampleService.getRentionSampleAttachment(rsid));
		return success(sample);
	}
	
	/**
	 * 删除留样
	 */
	@GetMapping("/sample/school/deleteSample")
	public Object deleteSample(String rsid){
		if(StringUtil.isBlank(rsid)){
			return fail("留样id为空，删除失败！");
		}
		RetentionSample sample = retentionSampleService.selectByPrimaryKey(rsid);
		if(sample==null){
			return fail("留样信息不存在！");
		}else if(sample.getDeleteStatus()==1) {
			return fail("留样信息已经删除，不允许重复删除！");
		}
		sample.setDeleteStatus(1);
		sample.setLastOpUser(getTokenUser().getTrueName());
		sample.setLastOpDate(new Date());
		retentionSampleService.deleteByPrimaryKey(sample);
		return success("删除成功！");
	}
	
}

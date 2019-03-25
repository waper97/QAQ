/**   
* 
*/
package com.yj.hqbz.controller.schoolOperation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javassist.expr.NewArray;

import javax.annotation.Resource;

import oracle.net.aso.p;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.hqbz.model.schoolOperation.PracticeUser;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.schoolOperation.PracticeUserService;
import com.yj.hqbz.util.CommUtil;
import com.yj.hqbz.util.DateUtil;
import com.yj.hqbz.util.StringUtil;
import com.yj.hqbz.util.UUIDUtil;

/**   
 * @Title: PracticeUserController.java
 * @Package com.yj.hqbz.controller.schoolOperation 
 * @Description: TODO
 * @author xx   
 * @date 2019-3-8 
 */
@RestController
public class PracticeUserController extends BaseController {
	@Resource
	PracticeUserService practiceUserService;
	/**
	 * 查询人员列表
	 */
	@GetMapping("/people/school/getList")
	public Object getList(String name,String beginDate,String endDate,Integer orgid,Integer sex,String entryDate,DataGridModel model){
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
		param.put("name", name);
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
		
		param.put("sex", sex);
		Date entryTime=null;
		if(StringUtil.isNotBlank(endDate)){
			entryTime=DateUtil.getDateByStr(entryDate+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
		}
		param.put("entryDate", entryTime);
		
		PageInfo<PracticeUser> pageInfo=practiceUserService.getList(param,model.getPage(),model.getRows());
		return new BaseRes("OK",pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
	}
	
	/**
	 * 新增人员保存
	 */
	@PostMapping("/people/school/addSave")
	public Object addSave(PracticeUser user){
		UserInfo tokenUser = this.getTokenUser();
		if(tokenUser.getOrgId()!=null){
			user.setOrgid(tokenUser.getOrgId());
		}else{
			return fail("当前用户机构id为空，新增失败！");
		}
		
		if(StringUtil.isBlank(user.getName()) || user.getBeginDate()==null || user.getEndDate()==null
				|| StringUtil.isBlank(user.getPhone())||user.getSex()==null || user.getEntryDate()==null
				|| StringUtil.isBlank(user.getPicUrl()) || StringUtil.isBlank(user.getThumbnailUrl())){
			return fail("参数不能为空，新增失败！");
		}
		user.setStatus(0);
		user.setCreator(tokenUser.getTrueName());
		String uuid = UUIDUtil.getUUID();
		user.setUserid(uuid);
		user.setCreateDate(new Date());
		practiceUserService.addUser(user);
		return success("增加成功！");
	}
	
	/**
	 * 修改人员保存
	 */
	@PostMapping("/people/school/updateSave")
	public Object updateSave(PracticeUser user){
		if(user.getUserid()==null){
			return fail("用户id为空，修改失败！");
		}
		if(StringUtil.isBlank(user.getName()) || user.getBeginDate()==null || user.getEndDate()==null
				|| StringUtil.isBlank(user.getPhone())||user.getSex()==null || user.getEntryDate()==null
				|| StringUtil.isBlank(user.getPicUrl()) || StringUtil.isBlank(user.getThumbnailUrl())){
			return fail("参数不能为空，修改失败！");
		}
		UserInfo tokenUser = this.getTokenUser();
		user.setLastOpUser(tokenUser.getTrueName());
		user.setLastOpDate(new Date());
		practiceUserService.updateUser(user);
		return success("修改成功！");
	}
	
	/**
	 * 查看人员详情
	 */
	@GetMapping("/people/school/getPeople")
	public Object getPeople(String userid){
		if(StringUtil.isBlank(userid)){
			return fail("用户id为空，查询失败！");
		}
		PracticeUser practiceUser=practiceUserService.getPeople(userid);
		return success(practiceUser);
	}
	
	/**
	 * 删除人员
	 */
	@GetMapping("/people/school/deletePeople")
	public Object deletePeople(String userid){
		if(StringUtil.isBlank(userid)){
			return fail("用户id为空，删除失败！");
		}
		PracticeUser people = practiceUserService.getPeople(userid);
		if(people==null){
			return fail("人员不存在！");
		}else if(people.getStatus()==1){
			return fail("人员已经删除，不允许重复删除！");
		}
		people.setStatus(1);
		people.setLastOpUser(getTokenUser().getTrueName());
		people.setLastOpDate(new Date());
		practiceUserService.deletePeople(people);
		return success("删除成功！");
	}
}

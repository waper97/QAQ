/**   
* 
*/
package com.yj.hqbz.controller.schoolOperation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.yj.hqbz.model.schoolOperation.AttendanceIndex;
import com.yj.hqbz.model.schoolOperation.PracticeUser;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.schoolOperation.AttendanceDetailService;
import com.yj.hqbz.services.schoolOperation.AttendanceIndexService;
import com.yj.hqbz.util.DateUtil;
import com.yj.hqbz.util.StringUtil;
import com.yj.hqbz.util.UUIDUtil;

/**   
 * @Title: AttendanceController.java
 * @Package com.yj.hqbz.controller.schoolOperation 
 * @Description: TODO
 * @author xx   
 * @date 2019-3-9 
 */
@RestController
public class AttendanceController extends BaseController {
	@Resource
	AttendanceIndexService attendanceIndexService;
	@Resource
	AttendanceDetailService attendanceDetailService;
	
	/**
	 * 查询晨检记录列表
	 */
	@GetMapping("/attendance/school/getList")
	public Object getList(String beginDate,String endDate,String summary,
			String userid,Integer orgid,DataGridModel model){
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
		if(StringUtil.isNotBlank(summary)){
			param.put("summary", summary);
		}
		if(StringUtil.isNotBlank(userid)){
			param.put("userid", userid);
		}
		
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
		param.put("orderBy", model.getOrderBy());
		param.put("orderType", model.getOrderType());
		PageInfo<AttendanceIndex> pageInfo=attendanceIndexService.getList(param,model.getPage(),model.getRows());
		
		return new BaseRes("OK",pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
	}
	
	/**
	 * 新增晨检记录保存
	 */
	@PostMapping("/attendance/school/addSave")
	public Object addSave(@RequestBody AttendanceIndex attIndex){
		UserInfo tokenUser = this.getTokenUser();
		attIndex.setUserid(tokenUser.getUserid());
		attIndex.setCreator(tokenUser.getTrueName());//晨检人与创建人为同一人
		if(tokenUser.getOrgId()!=null){
			attIndex.setOrgid(tokenUser.getOrgId());
			attIndex.setOrgName(tokenUser.getOrgName());
		}else{
			return fail("该用户没有机构id，新增失败！");
		}
		
		attIndex.setAttDate(new Date());//晨检日期没有时分秒
		attIndex.setCreateDate(new Date());
		attIndex.setIndexid(UUIDUtil.getUUID());
		attendanceIndexService.addSave(attIndex);
		
		return success("新增成功！");
	}
	
	/**
	 * 修改晨检记录保存
	 */
	@PostMapping("/attendance/school/updateSave")
	public Object updateSave(@RequestBody AttendanceIndex attIndex){
		UserInfo tokenUser = this.getTokenUser();
		if(attIndex.getIndexid()==null){
			return fail("晨检id为空，修改失败！");
		}
		attIndex.setLastOpDate(new Date());
		attIndex.setLastOpUser(tokenUser.getTrueName());
		attendanceIndexService.updateSave(attIndex);
		return success("修改成功！");
	}
	
	/**
	 * 查看晨检记录
	 */
	@GetMapping("/attendance/school/getAttendance")
	public Object getAttendance(String indexid){
		if(StringUtil.isBlank(indexid)){
			return fail("晨检id为空，查询失败！");
		}
		AttendanceIndex attendanceIndex=attendanceIndexService.getById(indexid);
		return success(attendanceIndex);
	}
	
	/**
	 * 删除晨检
	 */
	@GetMapping("/attendance/school/deleteAttendance")
	public Object deleteAttendance(String indexid){
		if(StringUtil.isBlank(indexid)){
			return fail("晨检id为空，删除失败！");
		}
		AttendanceIndex index = attendanceIndexService.getById(indexid);
		if(index==null){
			return fail("晨检信息不存在！");
		}else if(index.getStatus()==1){
			return fail("晨检信息已删除，不允许重复删除！");
		}
		index.setStatus(1);
		index.setLastOpUser(getTokenUser().getTrueName());
		index.setLastOpDate(new Date());
		attendanceIndexService.deleteById(index);
		return success("删除成功！");
	}
}
	

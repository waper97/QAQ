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
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.hqbz.model.schoolOperation.DiningOut;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.schoolOperation.DiningOutService;
import com.yj.hqbz.util.DateUtil;
import com.yj.hqbz.util.StringUtil;
import com.yj.hqbz.util.UUIDUtil;

/**   
 * @Title: DinnerOutController.java
 * @Package com.yj.hqbz.controller.schoolOperation 
 * @Description: TODO
 * @author xx   
 * @date 2019-3-11 
 */
@RestController
public class DiningOutController extends BaseController {
	@Resource
	DiningOutService diningOutService;
	
	/**
	 * 查看出餐列表
	 */
	@GetMapping("/diningOut/school/getList")
	public Object getList(Integer menuType,String beginDate,String endDate,String creator,String orgid,
			Integer status,DataGridModel model ){
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
		param.put("creator", creator);
		param.put("status", status);
		
		PageInfo<DiningOut> pageInfo=diningOutService.getList(param,model.getPage(),model.getRows());
		
		return new BaseRes("OK",pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
	}
	
	/**
	 * 新增出餐保存
	 * 此时状态为未出餐，尚未填入验收人、出餐时间信息
	 */
	@PostMapping("/diningOut/school/addSave")
	public Object addSave(DiningOut dining){
		UserInfo user = this.getTokenUser();
		dining.setCreator(user.getTrueName());
		dining.setCreateDate(new Date());
		dining.setDeleteStatus(0);
		dining.setStatus(0);//未出餐状态
		dining.setId(UUIDUtil.getUUID());
		diningOutService.insert(dining);
		return success("新增成功！");
	}
	
	/**
	 * 出餐保存
	 * 此时状态为出餐
	 */
	@PostMapping("/diningOut/school/updateSave")
	public Object updateSave(DiningOut dining){
		UserInfo user = this.getTokenUser();
		dining.setLastOpUser(user.getTrueName());
		dining.setLastOpDate(new Date());
		if(StringUtil.isBlank(dining.getCookerName()) ||StringUtil.isBlank(dining.getChecker())
				||dining.getOutTime()==null){
			return fail("厨师、验收人、出餐时间不能为空，保存失败！");
		}
		//新增的菜单还是自动拉取的，新增的菜单menuCode以Z开头
		dining.setStatus(1);
		if(dining.getId()==null){
			return fail("出餐id不能为空，修改失败！");
		}
		diningOutService.updateSave(dining);
		return success("修改成功！");
	}
	
	/**
	 * 查看出餐
	 */
	@GetMapping("/diningOut/school/getDiningOut")
	public Object getDiningOut(String id,String menuCode){
		if(StringUtil.isBlank(id)&&StringUtil.isBlank(menuCode)){
			return fail("出餐id、menuCode都为空，查询失败！");
		}
		DiningOut diningOut=diningOutService.selectByPrimaryKey(id,menuCode);
		return success(diningOut);
	}
	
	/**
	 * 删除出餐
	 */
	@GetMapping("/diningOut/school/deleteDiningOut")
	public Object deleteDiningOut(String id){
		if(StringUtil.isBlank(id)){
			return fail("出餐id为空，删除失败！");
		}
		DiningOut diningOut=diningOutService.selectByPrimaryKey(id, null);
		if(diningOut==null){
			return fail("出餐信息不存在！");
		}else if(diningOut.getDeleteStatus()==1){
			return fail("出餐信息已经删除,不允许重复删除！");
		}
		diningOut.setDeleteStatus(1);
		diningOut.setLastOpDate(new Date());
		diningOut.setLastOpUser(getTokenUser().getTrueName());
		diningOutService.deleteById(diningOut);
		return success("删除成功！");
	}
	
}

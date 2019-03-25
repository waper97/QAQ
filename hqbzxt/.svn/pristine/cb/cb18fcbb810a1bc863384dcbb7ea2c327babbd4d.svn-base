package com.yj.hqbz.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.hqbz.model.system.Resources;
import com.yj.hqbz.model.system.Role;
import com.yj.hqbz.services.system.SystemRoleAndResService;

/**  
 * @Title: SystemRoleAndResController.java
 * @Package com.yj.hqbz.controller.system
 * @Description: TODO
 * @author xx
 * @date 2019-2-21
 */
@RestController
public class SystemRoleAndResController extends BaseController{
	
	@Resource
	SystemRoleAndResService authService;
	
	/**
	 * 获取所有角色信息
	 * @return
	 */
	@GetMapping("/role/manage/getRoleList")
	public Object listAllRoleForManage(){
		return success(authService.getRoleList(null));
	}
	
	/**
	 * 增加或修改保存角色信息
	 * @param role
	 * @return
	 */
	@PostMapping("/role/manage/saveRole")
	public Object saveRole(Role role){
		if(role.getId() == null){
			if(authService.getRoleByCode(role.getRoleCode())!=null)
				return fail("保存失败，该角色编码已存在，请重新输入！");
		}
		authService.saveRole(role);
		return success("保存成功！");
	}

	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@PostMapping("/role/manage/deleteRole")
	public Object deleteRole(Integer id){
		if(id == null){
			return fail("参数错误，操作非法！");
		}
		else{
			Role role = authService.getRoleById(id);
			if(role == null){
				return fail("参数错误，操作非法！");
			}
			else{
				if(role.getSystemRole()==1){
					return fail("系统默认角色，不允许删除！");
				}
				else{
					authService.deleteRole(id);
					return success("删除成功！");
				}
			}
		}			
	}
	
	
	//=========================资源管理====================
	
	/**
	 * 增加权限资源
	 * @param res
	 * @return
	 */
	@PostMapping("/res/manage/save")
	public Object saveResource(Resources res){
		authService.saveResource(res);
		return new BaseRes("保存成功",res);
	}
	
	/**
	 * 删除权限资源
	 * @param id
	 * @return
	 */
	@PostMapping("/res/manage/delete")
	public Object deleteResource(Integer id){
		if(id == null){
			return fail("参数错误，操作非法！");
		}
		else{
			List<Resources> child = authService.getChildResourceByParentId(id);
			if(child != null && child.size() >0 ){
				return fail("该资源下尚有子资源，不允许删除！");
			}
			authService.deleteResource(id);
			return success("删除成功！");
		}
	}
	
	/**
	 * 根据父节点获取子资源
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/res/manage/getResList")
	public Object listResourcesByParentId(Integer parentid){
		if(parentid == null){
			parentid = 0;
		}
		List<Resources> child = authService.getChildResourceByParentId(parentid);
		return success(child);
	}
	
	
	/**
	 * 获取资源树
	 * @return
	 */
	@GetMapping("/res/manage/getResTree")
	public Object genereateResourceTree(Integer resType){
		List<Object> lst = new ArrayList<Object>();
		lst.add(authService.generateResourceTree(resType));
		return success(lst);
	}
	
	//================角色授权=========================
	
	/**
	 * 根据角色ID获取该角色已被授权的资源权限
	 * @param roleid
	 * @return
	 */
	@GetMapping("/role/manage/getRoleRes")
	public Object getRoleRights(Integer roleid){
		if(roleid == null){
			return fail("参数错误，操作非法！");
		}
		else{
			return success(authService.getRoleHavedResId(roleid));
		}
	}
	
	/**
	 * 保存角色资源
	 * @param roleid
	 * @param res
	 * @return
	 */
	@PostMapping("/role/manage/saveRoleRes")
	public Object saveRoleRes(Integer roleid,String res){
		if(roleid == null){
			return fail("参数错误，操作非法！");
		}
		else{
			authService.saveRoleHaveRes(roleid, res);
			return success("授权成功！");
		}
	}
	
	
}

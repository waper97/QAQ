package com.yj.hqbz.services.system;

import java.util.List;

import com.yj.common.model.UserRightInfo;
import com.yj.hqbz.model.system.Resources;
import com.yj.hqbz.model.system.Role;

/**  
 * @Title: SystemRoleAndResService.java
 * @Package com.yj.hqbz.services.system
 * @Description: TODO
 * @author xx
 * @date 2019-2-21
 */
public interface SystemRoleAndResService {

	//根据角色类型获取角色列表
	List<Role> getRoleList(Integer roleType);
		
	//保存角色信息
	void saveRole(Role role);
	
	//删除角色信息
	void deleteRole(Integer roleId);
	
	//根据角色编码获取角色详情
	Role getRoleByCode(String roleCode);
	
	Role getRoleById(int  roleId);
	
	//获取所有的资源列表（用于权限验证）
	List<Resources> getAllResource();
	
	//根据上级资源ID获取子资源列表
	List<Resources> getChildResourceByParentId(Integer parentId);
	
	//保存资源信息
	void saveResource(Resources resource);
	
	//删除资源
	void deleteResource(Integer id);
	
	//生成资源树
	Object generateResourceTree(Integer resType);
	
	//根据角色ID获取该角色拥有的权限集
	List<Integer> getRoleHavedResId(Integer roleid);
	
	//保存角色拥有的权限集
	void saveRoleHaveRes(Integer roleid,String resIds);
	
	//根据用户ID和角色类型取得该用户在系统中的角色和权限等信息
	UserRightInfo getUserRolesAndRightInfo(String userId,int roleType); 
	
	void clearUserRoleAndRight(String userid);
}

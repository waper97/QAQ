package com.yj.hqbz.model.system;

import java.util.List;

/**  
 * @Title: RoleRights.java
 * @Package com.yj.hqbz.model.system
 * @Description: TODO
 * @author xx
 * @date 2019-2-22
 */
public class UserRoleTypeRights {
	private int roleType;  // 0 后台 1卖家 2 买家
	private List<Role> roles; //对应的角色列表
	List<Resources> rights;  //全部资源集合
	List menu;	//全部菜单集合
	
	public int getRoleType() {
		return roleType;
	}
	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public List<Resources> getRights() {
		return rights;
	}
	public void setRights(List<Resources> rights) {
		this.rights = rights;
	}
	public List getMenu() {
		return menu;
	}
	public void setMenu(List menu) {
		this.menu = menu;
	}
	
}

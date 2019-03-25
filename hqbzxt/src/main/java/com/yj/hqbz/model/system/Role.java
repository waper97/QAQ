package com.yj.hqbz.model.system;

public class Role {
    private Integer id;

    private String roleCode;

    private String roleName;

    private Integer roleType;

    private String remark;

    private Integer status = 0;
    
    private Integer systemRole = 0;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSystemRole() {
		return systemRole;
	}

	public void setSystemRole(Integer systemRole) {
		this.systemRole = systemRole;
	}
    
    
}
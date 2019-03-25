package com.yj.hqbz.mapper.system;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.system.Role;

public interface RoleMapper {
	
    Role selectByPrimaryKey(Integer id);
    
    List<Role> selectRolesByType(Integer type);
    
    Role selectByCode(String code);
    
    void insertRole(Role role);
    
    void updateRole(Role role);
    
    void deleteRole(Integer id);
    
    List<Role> selectUserHaveRoles(Map<String,Object> param);
    
    List<Role> selectUserRolesByOrgid(Integer orgid);
    
    void deleteRolesByUserId(String userid);
    
    void addUserHaveRoles(Map<String,Object> map);
    
}
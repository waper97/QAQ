package com.yj.hqbz.mapper.system;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.system.Resources;

public interface ResourcesMapper {
    int insert(Resources record);
    
    void update(Resources record);
    
    void delete(Integer id);
    
    void cascadeDeleteResInRoleRes(Integer id);
    
    //根据父资源ID获取其直接子资源
    List<Resources> selectChildResourceByParentId(Integer parentId);
	//根据父资源ID获取其子孙资源
	List<Resources> selectAllChildResourcesByParentId(Map<String,Object> param);
	//根据角色ID获取该角色被授权的权限ID
	List<Integer> selectRoleHavedResId(Integer roleid);
	//根据角色ID删除该角色的权限
	void deleteRoleHavedRes(Integer roleid);
	//保存角色权限
	void insertRoleHavedRes(Map<String,Object> param);
	
	//根据用户ID和角色类型获取该用户所拥有的所有权限
	List<Resources> getUserHaveRights(String userid,Integer resType);
	
	List<Resources> getUserHaveMenusRights(String userid,Integer resType);
	
}
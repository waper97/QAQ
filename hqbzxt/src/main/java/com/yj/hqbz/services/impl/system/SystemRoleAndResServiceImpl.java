package com.yj.hqbz.services.impl.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yj.common.model.TreeNode;
import com.yj.common.model.UserRightInfo;
import com.yj.hqbz.mapper.system.ResourcesMapper;
import com.yj.hqbz.mapper.system.RoleMapper;
import com.yj.hqbz.mapper.user.UserInfoMapper;
import com.yj.hqbz.model.system.Resources;
import com.yj.hqbz.model.system.Role;
import com.yj.hqbz.model.system.UserRoleTypeRights;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.system.SystemRoleAndResService;
import com.yj.hqbz.util.StringUtil;

@Service
@CacheConfig(cacheNames = "user_rights")  
public class SystemRoleAndResServiceImpl implements SystemRoleAndResService {
	
	@Resource
	RoleMapper roleMapper;	
	@Resource
	ResourcesMapper resMapper;
	@Resource
	UserInfoMapper userMapper;
	
	//========================角色管理========================
	public List<Role> getRoleList(Integer roleType) {
		return roleMapper.selectRolesByType(roleType);
	}

	@Transactional
	public void saveRole(Role role) {
		if(role.getId() == null){
			roleMapper.insertRole(role);
		}
		else
			roleMapper.updateRole(role);
	}

	@Transactional
	public void deleteRole(Integer roleId) {
		roleMapper.deleteRole(roleId);
	}
	
	public Role getRoleByCode(String roleCode) {
		return roleMapper.selectByCode(roleCode);
	}
	
	public Role getRoleById(int  roleId){
		return roleMapper.selectByPrimaryKey(roleId);
	}
	//=========================资源管理===============================
	
	/**
	 * 增加/修改保存资源
	 * @param resource
	 */
	@Transactional
	public void saveResource(Resources resource){
		if(resource.getId()==null){
			resMapper.insert(resource);
		}
		else
			resMapper.update(resource);
	}
	
	/**
	 * 根据父资源获取下级资源
	 * @param parentId
	 * @return
	 */
	public List<Resources> getChildResourceByParentId(Integer parentId){
		return resMapper.selectChildResourceByParentId(parentId);
	}
	
	public List<Resources> getAllResource(){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("parentId", 0);
		
		return resMapper.selectAllChildResourcesByParentId(param);
	}
	
	/**
	 * 删除资源
	 * @param id
	 */
	@Transactional
	public void deleteResource(Integer id){
		resMapper.delete(id);
		resMapper.cascadeDeleteResInRoleRes(id);
	}
	
	
	public Object generateResourceTree(Integer resType) {
		//首先取得父节点为0的节点信息
		TreeNode tree = new TreeNode();
		tree.setId("0");
		tree.setText("资源权限树");
		tree.setType("0");
		tree.setIconCls("icon-root");		
		tree.setParentid("");
		tree.setRemark("");
		tree.setSortno(0);
		tree.setIsshow(1); //0:不显示，1：显示
		tree.setStatus(0);
		tree.setHtmlUrl("");
		tree.setApiUrl("");
		tree.setApiUrl("");
		tree.setScope(3);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("parentId", 0);
		if(resType!=null){
			param.put("type", resType);
		}
		List<Resources> resList = resMapper.selectAllChildResourcesByParentId(param);		
		tree.setChildren(buildResChild(resList,0,0));	
		return tree;
	}
	
	private List<TreeNode> buildResChild(List<Resources> resList,int parentId,int begin){
		List<TreeNode> child = new ArrayList<TreeNode>();
		int size = resList.size();

		for(int i = begin;i<size;++i){
			Resources res = resList.get(i);
			if(res.getParentid().intValue() == parentId){  
				TreeNode node = new TreeNode();
				node.setId(res.getId().toString());
				node.setText(res.getName());
				node.setUrl(res.getHtmlUrl());
				node.setType(res.getRightType().toString());
				node.setApiUrl(res.getApiUrl());
				node.setHtmlUrl(res.getHtmlUrl());
				node.setStatus(res.getStatus());
				node.setIsshow(res.getIsshow());
				node.setSortno(res.getSortno());
				node.setRemark(res.getRemark());
				node.setParentid(res.getParentid().toString());
				node.setIconCls(res.getIcon());
				node.setScope(res.getScope());
				List<TreeNode> _child = buildResChild(resList,res.getId(),i+1);
				if(_child!=null && _child.size()>0){
					node.setChildren(_child);
					node.setState("closed");
				}
				child.add(node);
			}			
		}		
		return child;
	}
	
	@CacheEvict(key = "#p0")
	public void clearUserRoleAndRight(String userid){
		
	}
	
	/**
	 * 根据用户ID获取该用户的角色等信息
	 */
	@CachePut(key = "#p0")
	public UserRightInfo getUserRolesAndRightInfo(String userId,int roleType){
		UserInfo u = userMapper.selectByPrimaryKey(userId);
		if(u == null || u.getStatus()==1){
			return null;
		}
		else{
			UserRightInfo rightInfo = new UserRightInfo();
			rightInfo.setId(userId);
			rightInfo.setName(u.getTrueName());
			
			UserRoleTypeRights roleRight = new UserRoleTypeRights();
			roleRight.setRoleType(roleType);
			List<Role>  roles = getUserHaveRoles(userId, roleType);
			if(roles != null && roles.size()>0){
				roleRight.setRoles(roles);
				roleRight.setMenu(getUserHaveMenus(userId,roleType));
				roleRight.setRights(getUserHaveRights(userId,roleType));
				rightInfo.setRolesAndRights(roleRight);
			}
			
			return rightInfo;
		}
	}
	
	/**
	 * 根据用户ID和角色类型获取该类型的角色
	 * @param userid
	 * @return
	 */
	private List<Role> getUserHaveRoles(String userid,int roleType){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("userId", userid);
		param.put("roleType", roleType);
		return roleMapper.selectUserHaveRoles(param);
	}
	
	/**
	 * 根据用户ID和角色类型ID获取该用户可访问的所有权限
	 * @param userid
	 * @param roleType
	 * @return
	 */
	private List<Resources> getUserHaveRights(String userid,int roleType){
		return resMapper.getUserHaveRights(userid, roleType);
	}
	 
	/**
	 * 根据用户ID和角色ID获取该用户可访问的菜单集合
	 * @param userid
	 * @param roleType
	 * @return
	 */
	private List getUserHaveMenus(String userid,int roleType){
		Map<String,Object> parmas = new HashMap<>();
		parmas.put("userid",userid);
		parmas.put("roleType",roleType);
		List<Resources> menusList = resMapper.getUserHaveMenusRights(parmas);
		return buildResChild(menusList,0,0);
	}
	
	//=============================================================
	public List<Integer> getRoleHavedResId(Integer roleid){
		return resMapper.selectRoleHavedResId(roleid);
	}
	
	@Transactional
	public void saveRoleHaveRes(Integer roleid,String resIds){
		resMapper.deleteRoleHavedRes(roleid);
		if(StringUtil.isNotBlank(resIds)){
			String[] arr = resIds.split(",");
			Map<String,Object> roleRes = new HashMap<String,Object>();
			roleRes.put("roleid", roleid);
			for(int i = 0 ;i<arr.length ; ++i){
				roleRes.put("resid", arr[i]);
				resMapper.insertRoleHavedRes(roleRes);
			}
		}
	}
}

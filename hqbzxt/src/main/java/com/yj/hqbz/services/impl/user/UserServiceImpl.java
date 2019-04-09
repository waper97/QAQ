package com.yj.hqbz.services.impl.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.org.OrgBelongMapper;
import com.yj.hqbz.mapper.org.OrganizationMapper;
import com.yj.hqbz.mapper.system.RoleMapper;
import com.yj.hqbz.mapper.user.UserInfoMapper;
import com.yj.hqbz.model.org.OrgBelong;
import com.yj.hqbz.model.org.Organization;
import com.yj.hqbz.model.system.Role;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.user.UserService;
import com.yj.hqbz.util.CommUtil;
import com.yj.hqbz.util.StringUtil;
import com.yj.hqbz.util.UUIDUtil;
import com.yj.hqbz.util.tmthreadpool.TmThreadPool;
import com.yj.hqbz.web.Global;
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserInfoMapper userMapper;
    @Resource
    RoleMapper roleMapper;
    @Resource
    OrganizationMapper orgMapper;
    @Resource
    private OrgBelongMapper orgBelongMapper;
    
    @Value("${sms.address}")
    private String smsUrl;
    
    
    public UserInfo getUserByUserName(String username) {
    	return userMapper.selectUserByUserName(username);
    }
    
    @Transactional
    public void createUser(UserInfo user,String opUserid,int userRole) {
        user.setUserid(UUIDUtil.getUUID());
        user.setAddTime(new Date());
        user.setDeleteStatus(0);       
        user.setStatus(0);
        user.setAddPerson(opUserid);
        user.setUserRole(userRole);
        if(Global.getSystemParamIntValueByKey("is_debug")==0){  //正式环境
        	String pwd = CommUtil.randomString(6);
        	user.setPassword(StringUtil.MD5(pwd));
        	
        	//发短信
            String[] args = new String[1];
            args[0] = pwd;
            TmThreadPool.sendSms(smsUrl, user.getMobile(), "verifyCode", args);
        }
        else{
        	user.setPassword(StringUtil.MD5("123456"));
        }
        userMapper.insert(user);
        
        
    }
    
    
    public PageInfo<UserInfo> getUserList(UserInfo user,int page,int rows) {
        PageHelper.startPage(page,rows);        
        List<UserInfo> userList = userMapper.getUserList(user);
        PageInfo<UserInfo> info=new PageInfo<UserInfo>(userList);
        return info;
    }
    
    
    public UserInfo getUserById(String userid) {
        return userMapper.selectByPrimaryKey(userid);
    }
    
    
    @Transactional
    public void updateUserById(UserInfo user) { 
       userMapper.updateUserInfo(user);       
    }
    
    
    @Transactional
    public void resetPwd(String userid,String pwd) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userid", userid);
        params.put("password", StringUtil.MD5(pwd));        
        userMapper.resetPwdById(params);
        
        UserInfo user = userMapper.selectByPrimaryKey(userid);
        if(user != null){
            //发短信
            String[] args = new String[1];
            args[0] = pwd;
            TmThreadPool.sendSms(smsUrl, user.getMobile(), "verifyCode", args);
        }
    }
    
    
    @Transactional
    public void updatePwd(String userid,String pwd){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userid", userid);
        params.put("password", pwd);        
        userMapper.resetPwdById(params);
    }
    
    
    public List<Role> getRoleListByOrgid(Integer orgid) {
        List <Role> roleList = null;
        if(orgid == -1){
            roleList = roleMapper.selectRolesByType(0);
        }else{
            roleList = roleMapper.selectUserRolesByOrgid(orgid);
        }
        return roleList;
    }
    
    
    public List<Role> getUserHaveRoles(String userid) {
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("userId", userid);
       return roleMapper.selectUserHaveRoles( param);
    }
    
    
    @Transactional
    public void saveUserRoles(String userid, String roles) {
       roleMapper.deleteRolesByUserId(userid);
       if(StringUtils.isNotBlank(roles)){
           String[] rolesArray = roles.split(",");
           Map<String,Object> params = new HashMap<String,Object>();
           params.put("userid", userid);
           for(String role:rolesArray){
               params.put("roleid", role);
               roleMapper.addUserHaveRoles(params);
           }
       }
       
    }
    
    public UserInfo selectByPrimaryKey(String userid) {
    	return userMapper.selectByPrimaryKey(userid);
    }
    
    
    public UserInfo getUserByPhone(String phone) {       
        return userMapper.getUserByPhone(phone);
    }
    
    
    @Transactional
    public void updateUserInfo(UserInfo user) {     
       userMapper.updateUserInfo(user);     
    }
    
    public PageInfo<UserInfo> getOrgUserList(String[] orgIds,String keywords,int page,int rows){
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("orgIdArr", orgIds);
    	param.put("keywords", orgIds);
    	PageHelper.startPage(page,rows);        
        List<UserInfo> userList = userMapper.getOrgUserList(param);
        PageInfo<UserInfo> info=new PageInfo<UserInfo>(userList);
        return info;
    	
    }
    
    public OrgBelong getBelongSchoolByOrgid(Integer orgid) {
        return orgBelongMapper.getBelongByOrg(orgid);
    }
}

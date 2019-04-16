package com.yj.hqbz.services.user;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.org.OrgBelong;
import com.yj.hqbz.model.system.Role;
import com.yj.hqbz.model.user.UserInfo;

public interface UserService {

    //根据用户名获取用户
    UserInfo getUserByUserName(String username);
    //根据用户ID获取用户
    UserInfo getUserById(String userid);
    //创建用户
    void createUser(UserInfo user,String opUserid,int userRole);
    //根据ID更新用户信息
    void updateUserById(UserInfo user);
    //根据条件查询用户列表
    PageInfo<UserInfo> getUserList(UserInfo user,int page,int rows );
    //根据机构查用户
    PageInfo<UserInfo> getOrgUserList(String[] orgIds,String keywords,int page,int rows);
    
    //重置密码
    void resetPwd(String userid,String pwd);
    //根据用户机构ID获取用户的权限
    List<Role> getRoleListByOrgid(Integer orgid);
    //保存用户权限
    void saveUserRoles(String userid,String roles);
    //根据ID获取用户
    UserInfo selectByPrimaryKey(String userid);
    //根据电话获取用户
    UserInfo getUserByPhone(String phone);
    //更新密码
    void updatePwd(String userid,String pwd);
    //根据用户ID获取该用户拥有的角色
    List<Role> getUserHaveRoles(String userid);
    //个人资料维护
    void updateUserInfo(UserInfo user);
    //根据用户机构ID获取学校
    OrgBelong getBelongSchoolByOrgid(Integer orgid);

}
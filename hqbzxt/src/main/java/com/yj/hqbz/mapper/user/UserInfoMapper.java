package com.yj.hqbz.mapper.user;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.user.UserInfo;

public interface UserInfoMapper {
    int insert(UserInfo record);
    
    UserInfo selectByPrimaryKey(String userid);
    //根据用户名获取用户
    UserInfo selectUserByUserName(String username);
    //根据用户手机号获取用户
    UserInfo getUserByPhone(String phone);
    //更新用户信息
    void updateUserInfo(UserInfo user);
    //根据条件查询用户
    List<UserInfo> getUserList(UserInfo user);
    
    List<UserInfo> getOrgUserList(Map<String,Object> param);
    //根据用户ID重置密码
    void resetPwdById(Map<String,Object> map);

    List<UserInfo> getUserByOrg(Integer orgid);
    
}
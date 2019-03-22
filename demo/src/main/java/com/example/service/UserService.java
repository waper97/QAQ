package com.example.service;

import com.example.model.User;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * create by ${user} on 2019/3/21
 * *
 **/
public interface UserService {

    /**
     * 查询
     * @param pageNum
     * @param PageSize
     * @return
     */
    PageInfo<User> getUserList(int pageNum, int pageSize, Map<String,Object> paramMap);

    /**
     * 条件查询用信息
     * @param userName
     * @param password
     * @return
     */
   User getUserInfo(String userName,String password);


    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}

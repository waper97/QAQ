package com.waper.service;

import com.github.pagehelper.PageInfo;
import com.waper.model.Users;
import org.apache.catalina.User;

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
    PageInfo<Users> getUserList(int pageNum, int pageSize, Map<String,Object> paramMap);

    /**
     * 条件查询用信息
     * @param userName
     * @param password
     * @return
     */
   Users getUserInfo(String userName, String password);


    int deleteByPrimaryKey(String id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

}

package com.waper.shoppingcenter.service;

import com.github.pagehelper.PageInfo;
import com.waper.shoppingcenter.model.User;

import java.util.Map;

/**
 * create by  on 2019/6/3
 * *
 **/
public interface UserService {
    PageInfo<User> listUser(Map<String,Object> paramMap, int pageNumber, int pageSize);

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    User getLogin(String userName, String password);

    /**
     * 查询用户名是否存在
     * @param userName
     * @return
     */
    User findUserByUserName(String userName);


    /**
     * 用户注册
     * @param user
     * @return
     */
    int userRegistered(User user);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int update(User user);

}

package com.waper.shoppingcenter.service;

import com.github.pagehelper.PageInfo;
import com.waper.shoppingcenter.model.User;

/**
 * create by  on 2019/6/3
 * *
 **/
public interface UserService {
    PageInfo<User> getUseList(int pageNumber, int pageSize);

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    User getLogin(String userName, String password);

}

package com.waper.shoppingcenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waper.shoppingcenter.dao.user.UserMapper;
import com.waper.shoppingcenter.model.User;
import com.waper.shoppingcenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * create by  on 2019/6/3
 * *
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public PageInfo<User> getUseList(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<User> userList  = userMapper.listUser(new HashMap<String,Object>(16));
        PageInfo<User>  pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @Override
    public User getLogin(String userName, String password) {
        return userMapper.getLogin(userName, password);
    }
}

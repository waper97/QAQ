package com.waper.shoppingcenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waper.shoppingcenter.dao.user.UserMapper;
import com.waper.shoppingcenter.model.User;
import com.waper.shoppingcenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by  on 2019/6/3
 * *
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public PageInfo<User> listUser(Map<String,Object> paramMap, int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<User> userList  = userMapper.listUser(new HashMap<String,Object>(16));
        PageInfo<User>  pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @Override
    public User getLogin(String userName, String password) {
        return userMapper.getLogin(userName, password);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userMapper.findUserByUserName(userName);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int userRegistered(User user) {
        return userMapper.insert(user);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }


}

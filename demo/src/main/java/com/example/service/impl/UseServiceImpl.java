package com.example.service.impl;

import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * create by ${user} on 2019/3/21
 * *
 **/
@Service
public class UseServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    public UseServiceImpl() {
        super();
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public User getUserInfo(String userName, String password) {
        return userMapper.getUserInfo(userName,password);
    }

    @Override
    public PageInfo<User> getUserList(int pageNum, int pageSize, Map<String,Object> paramMap) {
        PageHelper.startPage(pageNum,pageSize);

        List<User> user = userMapper.getUserList(paramMap);
        PageInfo<User> pg = new PageInfo<>(user);
        return pg;
    }
}

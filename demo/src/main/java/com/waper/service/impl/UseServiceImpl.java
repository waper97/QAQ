package com.waper.service.impl;

import com.waper.mapper.UsersMapper;
import com.waper.model.Users;
import com.waper.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.User;
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
    UsersMapper userMapper;

    public UseServiceImpl() {
        super();
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Users record) {
        record.setStatus(1);
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(Users record) {
        return 0;
    }

    @Override
    public Users selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Users record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Users record) {
        return 0;
    }





    @Override
    public PageInfo<Users> getUserList(int pageNum, int pageSize, Map<String,Object> paramMap) {
        PageHelper.startPage(pageNum,pageSize);

        List<Users> user = userMapper.getUserList(paramMap);
        PageInfo<Users> pg = new PageInfo<>(user);
        return pg;
    }

    @Override
    public Users getUserInfo(String userName, String password) {
        return null;
    }
}

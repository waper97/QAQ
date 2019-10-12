package com.waper.shoppingcenter.service.impl;

import com.waper.shoppingcenter.dao.UserMapper;
import com.waper.shoppingcenter.model.User;
import com.waper.shoppingcenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * create by  on 2019/6/3
 * *
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public Page<User> getUseList(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber-1,pageSize);
        return  userMapper.findAll(pageable);
    }
}

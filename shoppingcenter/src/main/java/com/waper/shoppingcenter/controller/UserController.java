package com.waper.shoppingcenter.controller;

import com.waper.shoppingcenter.common.UUIDUtil;
import com.waper.shoppingcenter.dao.UserDao;
import com.waper.shoppingcenter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserDao userDao;

    /**
     * 用户登陆
     * @return
     */
    @RequestMapping("shop/user/login")
    public Object doLogin(String username,String password){
        if(username == null || password == null){
            throw new RuntimeException("用户名或密码不能为空");
        }
        // md5加密
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(md5Password);
        User user = userDao.getUserByUsernameAndPassword(username,md5Password);
       return user;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("shop/user/userRegister")
    public Object userRegister(User user){
        if (user.getUsername() == null || user.getPassword() == null){
            throw new RuntimeException("用户名或密码不能为空");
        }

        // 密码加密
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setId(UUIDUtil.getUUID());
        user.setPassword(md5Password);
        user.setRole(1);
        user.setStatus(0);
        userDao.save(user);
        return "添加成功";
    }



}

package com.waper.shoppingcenter.controller;

import com.github.pagehelper.PageInfo;
import com.waper.shoppingcenter.common.UUIDUtil;
import com.waper.shoppingcenter.model.User;
import com.waper.shoppingcenter.service.UserService;
import com.waper.shoppingcenter.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author wangpeng
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 用户登陆
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping("shop/user/login")
    public Object doLogin(String username, String password, HttpServletRequest request){
        if(username == null || password == null){
            throw new RuntimeException("用户名或密码不能为空");
        }
        // md5加密
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(md5Password);
        User user = userService.getLogin(username,md5Password);
        if (user == null){
            return new BaseResponse(false,"用户不存在");

        }else{
            request.setAttribute("USER_SESSION",user);
            String token = TokenUtil.createToken(user.getId(),"123456",60*60*1000);
            return new BaseResponse(user, token);
        }
    }
    @RequestMapping("shop/user/getSession")
    public Object getSession(HttpServletRequest request){

        return request.getAttribute("USER_SESSIOIN");
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("shop/user/userRegister")
    public Object userRegister(User user){
        if (user.getUsername() == null || user.getPassword() == null){
            return  new BaseResponse("用户名或密码不能为空");
        }
        User existUser = userService.findUserByUserName(user.getUsername());
        if (existUser != null) {
            return new BaseResponse("用户名已存在");
        }

        // 密码加密
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setId(UUIDUtil.getUUID());
        user.setPassword(md5Password);
        user.setRole(1);
        user.setStatus(0);
        user.setName("用户"+new Random());
        userService.insert(user);
        return  new BaseResponse(true,"添加成功");
    }
    /**
     *   获取用户列表
     */
    @RequestMapping("shop/user/getUserList")
    public Object getUserList(Integer pageNum, Integer pageSize){
        Map<String,Object> paramMap = new HashMap<>(16);
        PageInfo<User> user = userService.listUser(paramMap,pageNum,pageSize);
        return  new BaseResponse(true,user);
    }

    /**
     * 注销
     * @return
     */
    @RequestMapping("shop/user/logout")
    public Object logout(HttpServletRequest request, HttpServletResponse response){
        request.removeAttribute("USER_SESSION");
        return new BaseResponse(true,"退出成功");
    }

}

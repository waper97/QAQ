package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import com.example.util.CommUtil;
import com.example.util.TokenUtil;
import com.example.util.WebExecptionResolve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * create by ${user} on 2019/3/21
 * *
 **/
@RestController
public class UserController extends BaseController {

    HttpServletRequest request;
    HttpServletResponse response;
    @Autowired
    UserService userService;
    @RequestMapping("/getUserList")
    public Object getUserList(String name,Integer id,Integer age,String address){
        int page = 1;
        int size = 10;
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("name",name);
        paramMap.put("id",id);
        paramMap.put("age",age);
        paramMap.put("address",address);
        return userService.getUserList(1,10,paramMap);
    }
    @RequestMapping("/login")
    public Object getUserInfo(String userName,String password){

        String ip = request.getHeader("x－forwarded-for");
        System.out.println(ip);
        if(userName == null && "".equals(userName.toString())
                || password == null && "".equals(password)){
           return "用户名或密码不能为空";
        }
        User userInfo =  userService.getUserInfo(userName,password);
        if(userInfo != null){
            String token = TokenUtil.signature("123456",userInfo.getId(),15,userName);
            WebUtils.setSessionAttribute(request,"userInfo",userInfo);
            return  token;
        }
        return new WebExecptionResolve(false,"登陆失败");
    }
    @RequestMapping("/deleteUserByPrimarykey")
    public Object deleteUserByPrimarykey(String id) throws Exception {
        if(id == null && "".equals(id)){
            return new WebExecptionResolve(false,"参数不能为空",499);
        }
        int result = userService.deleteByPrimaryKey(id);
           if(result > 0){
               return new WebExecptionResolve(true,"删除成功",200);
        }
        return new WebExecptionResolve(false,"系统异常，删除失败",499);
    }

    @RequestMapping(value = "/updateUserByPrimaryKey",method = RequestMethod.POST)
    public Object updateUserByPrimaryKey(User user){
        if(user.getAddress() == null && "".equals(user.getAddress())){
            return new WebExecptionResolve(false,"参数不能为空",400);
        }
        int result = userService.updateByPrimaryKeySelective(user);
        if(result > 0){
            return new WebExecptionResolve(true,"修改成功",200);
        }
        return new WebExecptionResolve(false,"系统异常删除失败",2333);
    }
    @RequestMapping("/addUser")
    public Object addUser(User user){
        if(user.getAddress() == null||user.getAge() == null || user.getPassword()== null ||
        user.getUsername() == null){
            return new WebExecptionResolve(false,"必填信息不能为空",400);
        }
        user.setId(CommUtil.getUUid());
        int result = userService.insert(user);
        if(result > 0){
            return new WebExecptionResolve(true,"添加成功",200);
        }
        return new WebExecptionResolve(false,"添加失败",2333);
    }

    public static void main(String[] args) {
        System.out.println(UserController.recursion(5));

    }

    public  static  int  recursion(int n){
        if(n == 1) {
            return 1;
        }else{
            return n*recursion(n-1);
        }
    }
    public static int Fribonacci(int n){
    if(n<=2)
        return 1;
     else
        return Fribonacci(n-1)+Fribonacci(n-2);
    }

    @Override
    protected String getIP() {
        throw new NullPointerException();
    }
}

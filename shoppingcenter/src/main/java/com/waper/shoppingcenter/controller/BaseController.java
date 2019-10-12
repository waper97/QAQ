package com.waper.shoppingcenter.controller;

import com.waper.shoppingcenter.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BaseController {
//    @Autowired
//    private GoodsMapper goodsMapper;
//
//    private static final int SUCCESS = 200;
//    private static final int FAILURE = 400;
//
//    private int success;
//    private int failure;
//
//
//
//    @RequestMapping("hello")
//    public String helloSpringboot(){
//        return "hello boot-jpa";
//    }
//    @RequestMapping("toHello")
//    public String toHello(ModelMap modelMap){
//        Goods goods = new Goods();
//        goods.setId("1");
//        goods.setName("王鹏");
//        goods.setType("1");
//        goodsMapper.save(goods);
//        modelMap.put("test",goods);
//        return "hellospringboot";
//    }

    public Object getSession(HttpServletRequest request){
      return   request.getAttribute("USER_SESSION");
    }

}

package com.waper.filter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title
 * @Description:   自定义拦截器
 * @Author:         wangpeng
 * @CreateDate:     2019/3/21 18:02
 */
public class UserInterceper implements HandlerInterceptor {


//      请求前拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
////      获取token
////        String token = request.getHeader("accessToken");
//        String token =  (String)request.getSession().getAttribute("accessToken");
////      获取请求地址
//        String url = request.getRequestURI();
//        if(url.endsWith("login")){
//            return true;
//        }
//        if(token != null) {
//            boolean result = TokenUtil.verifyToken(token);
//            if(result)
//                return true;
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

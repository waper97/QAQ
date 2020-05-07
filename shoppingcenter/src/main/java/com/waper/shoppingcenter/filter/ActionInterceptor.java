package com.waper.shoppingcenter.filter;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * @ClassName ActionInterceptor
 * @Description TODO
 * @Author wangpeng
 * @Date 2020/4/9 17:06
 */
public class ActionInterceptor extends HandlerInterceptorAdapter {

    /**
     *debug标识
     */
    public static final boolean DEBUGFLAG = false;
    /**
     * 请求前拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (DEBUGFLAG){
//            return false;
//        }else {
//            return false;
//        }
//
//
//        // 从http请求中取出token
//        BaseResponse bas = null;
//        PrintWriter pw = null;
//        String requestMethod = request.getHeader("token");
//        //第一次请求的方式
//        String method ="OPTION";
//        if (method.equals(request.getMethod())) {
//            return true;
//        }
//        // 获取当前请求的token,并判断是否为空
//        String token = getToken(request);
//        if (StringUtils.isEmpty(token)) {
//            bas = new BaseResponse(false,BaseResponse.ACCSESS_TOKEN_EXPIRE_CODE,"登录失效，请重新登录！");
//        }else{
//            TokenUtil.verifyToken(token);
//        }
//        if (bas != null) {
////            pw = response.getWriter();
////            pw.write(JSONObject.toJSONString(bas));
//            return true;
//        }else{
//            return false;
//        }
        return true;
    }

    /***
     * 获取token
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("Authorization");
        }
        return token;
    }

}

package com.yj.common.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.alibaba.fastjson.JSONObject;
import com.yj.common.model.BaseRes;
import com.yj.common.model.UserRightInfo;
import com.yj.common.tools.TokenUtil;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.system.SystemRoleAndResService;
import com.yj.hqbz.services.user.UserService;
import com.yj.hqbz.util.StaticUtils;
import com.yj.hqbz.util.StringUtil;


/**
 * 拦截器
 */
public class ActionInterceptor extends HandlerInterceptorAdapter {
	
	@Resource
	SystemRoleAndResService authService;
	@Resource
	UserService userService;

	
	private BaseRes bas=null;
	private PrintWriter out;
	// 请求前拦截
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) {
		if(StaticUtils.debug)
			return true;
		else{
			if(request.getMethod().equals("OPTIONS")){
				return true;
			}
			//获取请求头token
			String url = request.getRequestURI();
			if(url.endsWith("login")){
				return true;
			}
			
			String token = request.getHeader("Authorization");
			try {
				//
				if (StringUtil.isNotBlank(token)) {
					//验证token
					TokenUtil.verifyToken(token);
					String userid = TokenUtil.getClaimStringValue(token, "userid");
					if(StringUtil.isNotBlank(userid)) {
						if(checkUserVisitValid(userid,request.getRequestURI())){
							return true;
						}
						else{
							bas = new BaseRes(false, BaseRes.FAILURE_CODE, "操作非法，您无权进行该操作，请与系统管理员联系！");
						}
					}
					else{
						bas = new BaseRes(false, BaseRes.LOGIN_CODE, "登录失效，请重新登录！");
					}
				}else{ //判断session
					UserInfo user = (UserInfo) WebUtils.getSessionAttribute((HttpServletRequest) request, "userInfo");
					if(user == null){
						bas = new BaseRes(false, BaseRes.LOGIN_CODE, "登录失效，请重新登录！");
					}
					else{
						if(checkUserVisitValid(user.getUserid(),request.getRequestURI())){
							return true;
						}
						else{
							bas = new BaseRes(false, BaseRes.FAILURE_CODE, "操作非法，您无权进行该操作，请与系统管理员联系！");
						}
					}				
				}			
			} catch (Exception e) {
				bas = new BaseRes(false, BaseRes.ACCSESS_TOKEN_EXPIRE_CODE, "登录失效，请重新登录！");
			}
			
			if(bas!=null){
				String origin = request.getHeader("Origin");
				this.outPrint(response, origin,bas);
				bas=null;
				return false;
			}else{
				return true;
			}
		}		
	}
	
	
	private boolean checkUserVisitValid(String userid,String url){
		UserRightInfo userRight = authService.getUserRolesAndRightInfo(userid,userService.getUserById(userid).getUserRole());
		if(userRight.getRolesAndRights()!=null){
			return StringUtil.checkVisitUrlIsValid(url, userRight.getRolesAndRights().getRights());
		}
		else
			return false;
	}
	
	/*
	 * //请求中拦截 public void postHandle(HttpServletRequest
	 * request,HttpServletResponse response, Object handler,ModelAndView
	 * modelAndView) throws Exception {
	 * System.err.println("中："+System.currentTimeMillis()); } //请求后拦截 public
	 * void afterCompletion(HttpServletRequest request,HttpServletResponse
	 * response, Object handler, Exception ex)throws Exception {
	 * System.err.println("后："+System.currentTimeMillis()); }
	 */
	
	private void outPrint(HttpServletResponse response,String origin,BaseRes bas){
		try {
			BaseRes.setResponse(origin,response);
			out = response.getWriter();
			out.write(JSONObject.toJSONString(bas));
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
			out=null;
		}
	}
}
package com.yj.common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.WebUtils;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.yj.common.config.SpringUtils;
import com.yj.common.model.UserRightInfo;
import com.yj.common.tools.DesUtils;
import com.yj.common.tools.TokenUtil;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.system.SystemRoleAndResService;
import com.yj.hqbz.util.StringUtil;

/**
 * TOKEN过滤器
 *
 */
@WebFilter(urlPatterns = "*.html", filterName = "TokenFilter")
public class TokenFilter implements Filter {

	SystemRoleAndResService authService;
	
	/**
	 * 封装，不需要过滤的list列表
	 */
	private static List<Pattern> patterns = new ArrayList<Pattern>();
	private static DesUtils desUtil = new DesUtils();

	public void init(FilterConfig filterConfig) throws ServletException {
		patterns.add(Pattern.compile("login.html"));
		patterns.add(Pattern.compile("index.html"));
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {		
		 HttpServletRequest httpRequest = (HttpServletRequest) request;
		 HttpServletResponse httpResponse = (HttpServletResponse) response;
	     
		 if(authService == null){
			 authService = (SystemRoleAndResService)SpringUtils.getBean("systemRoleAndResServiceImpl");
		 }
		 
		 //获取请求的url
		 String url =httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		 
		 url=url.startsWith("/") && url.length() > 1?url.substring(1):url;
		 //判断是否需要过滤（不在patterns中的url都需要过滤）
		 if ((url.startsWith("admin") || url.startsWith("manage")) && !url.endsWith("login.html") && !isExclude(url) && url.indexOf("ueditor")<0){
				 UserInfo user = (UserInfo) WebUtils.getSessionAttribute((HttpServletRequest) request, "userInfo");
				 if(user == null){
				     httpResponse.sendRedirect(httpRequest.getContextPath()+"/admin/login.html");
                     return;
				 }
				 String userid = user.getUserid();
				 if(!checkUserHaveRights(userid,url,user.getUserRole())){
					 httpResponse.sendRedirect(httpRequest.getContextPath()+"/admin/login.html");
					 return;
				 }
			 
		 }
		 //放行
		 chain.doFilter(httpRequest, httpResponse);
		 return;
	}

	public void destroy() {
		
	}

	/**
	 * 是否排除过滤：true为排除，false为不排除过滤
	 * 
	 * @param url
	 * @return
	 */
	private boolean isExclude(String url) {
		for (Pattern pattern : patterns) {
			Matcher matcher = pattern.matcher(url);
			if (matcher.matches()) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isLogin(String token) {
		if(StringUtil.isBlank(token)){ //未完
			return true;
		}
		String _token = desUtil.decrypt(token);
		try {
			//验证token
			if(StringUtil.isNotBlank(_token)) {
				TokenUtil.verifyToken(_token);
				return true;
			}
		}catch (TokenExpiredException e) {
			new RuntimeException("token已过期").printStackTrace();
		}catch (Exception e) {
			new RuntimeException("token认证失败").printStackTrace();
		}
		return false;
	}
	
	/**
	 * 判断用户是否有权限
	 * @param url
	 * @return
	 */
	private boolean checkUserHaveRights(String userid,String url,int userRole){
		//读取Redis信息
		if(StringUtil.isNotBlank(userid)){
			UserRightInfo userRight = authService.getUserRolesAndRightInfo(userid,userRole);
			if(userRight.getRolesAndRights()!=null)
				return StringUtil.checkVisitUrlIsValid(url, userRight.getRolesAndRights().getRights());
		}
		return false;		
	}

}
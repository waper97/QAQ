package com.yj.common.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.util.WebUtils;

import com.yj.common.model.BaseRes;
import com.yj.common.tools.ChineseSpellingUtil;
import com.yj.common.tools.TokenUtil;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.system.OperationLogService;
import com.yj.hqbz.services.user.UserService;
import com.yj.hqbz.util.StaticUtils;
import com.yj.hqbz.util.StringUtil;
import com.yj.hqbz.util.tmthreadpool.TmThreadPool;




/**  
 * @Title: BaseController.java
 * @Package com.xjt.controller
 * @Description: TODO
 * @author xx
 * @date 2016-4-20
 */
@Controller
public class BaseController {
	
	@Resource
	protected HttpServletRequest request;
	@Resource
	protected HttpServletResponse response;
	@Resource
	private OperationLogService operationLogService;
	@Resource
	private UserService userService;
	
	/**获取token用户ID*/
	protected String getTokenUserid() {
		if(StaticUtils.debug)
			return "1111";
		else{
			String token = request!=null?request.getHeader("Authorization"):null;
			if(StringUtil.isNotBlank(token)){
				String userid = token!=null?TokenUtil.getClaimStringValue(token, "userid"):null;
				if(StringUtil.isNotBlank(userid))
					return userid;
				else
					throw new RuntimeException("重新登录");
			}
			else{
				UserInfo user = (UserInfo) WebUtils.getSessionAttribute((HttpServletRequest) request, "userInfo");
				if(user == null){
					throw new RuntimeException("重新登录");
				}
				else
					return user.getUserid();
			}	
		}
	}
	
	/**获取token用户名称*/
	protected UserInfo getTokenUser() {
		UserInfo user = userService.selectByPrimaryKey(getTokenUserid());
		if(user!=null) {
			return user;
		}else {
			throw new RuntimeException("用户不存在！");
		}
	}
	
	/**获取IP*/
	protected String getRequestAddr(){
		String ip = request.getHeader("x-forwarded-for");  
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getHeader("Proxy-Client-IP");  
		}  
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getHeader("WL-Proxy-Client-IP");  
		}  
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getRemoteAddr();  
		}  
		return ip;
	}
	
	/**生成拼音*/
	protected String getPinYin(String pinyin,String name) {
		if(StringUtil.isNotBlank(pinyin)) {
			return pinyin.trim().toUpperCase();
		}else {
			return ChineseSpellingUtil.getSpellingHead(name.trim());
		}
	}
	
	/**是否有特殊字符*/
	protected boolean haveSpecial(String src) {
        if (src == null) {
            return false;
        }
        int cpCount = src.codePointCount(0, src.length());
        int firCodeIndex = src.offsetByCodePoints(0, 0);
        int lstCodeIndex = src.offsetByCodePoints(0, cpCount - 1);
        for (int index = firCodeIndex; index <= lstCodeIndex;) {
            int codepoint = src.codePointAt(index);
            if(codepoint<32&&codepoint!=10&&codepoint!=13){
            	return true;
            }
        }
        return false;
	}
	
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public void setRequestAttribute(String attr,Object obj){
		getRequest().setAttribute(attr, obj);
	}
	
	
	/**
	 * 创建错误BaseRes信息
	 */
	public BaseRes fail(String msg){
		return new BaseRes(false,BaseRes.FAILURE_CODE,msg);
	}
	
	protected Object success(Object object){
		return new BaseRes(true,object);
	}
	/**
	 * response输出信息
	 * @param response
	 * @param str
	 */
	public void outputResponse(HttpServletResponse response ,String str){
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加日志
	 */
	protected void saveJournalLog(String content,String remark){
		//获取ip和service
		String ip= getRequestAddr();
		//调用线程池
		TmThreadPool.saveOperationLog(operationLogService, getTokenUserid(), getRequestAddr(),content,remark);
	}
	

}

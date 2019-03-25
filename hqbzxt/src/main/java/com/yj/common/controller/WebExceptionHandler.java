package com.yj.common.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSONObject;
import com.yj.common.model.BaseRes;
import com.yj.hqbz.model.system.ErrorLog;
import com.yj.hqbz.services.system.OperationLogService;
import com.yj.hqbz.util.DateUtil;


/**
 * @Title: WebExceptionHandler.java
 * @Package com.yjkj.core.exception
 * @Description: 统一异常处理
 * @author xx
 * @date 2017-3-6
 */
@ControllerAdvice
public class WebExceptionHandler{
	@Resource
	OperationLogService logService;
	
	private static final Logger LOGGER = Logger.getLogger(WebExceptionHandler.class);

	/**
	 * 异常拦截
	 */
	/*@ExceptionHandler(RuntimeException.class)运行时报错*/
	@ExceptionHandler(Exception.class)
	public void operateExp(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.error("************* ------ 异常信息已记录（"+ DateUtil.getStrByDate(new Date(), "yyyy-MM-dd HH:mm:ss")+ "） ------- ***********");
		LOGGER.error(ex.getMessage());
		
		ex.printStackTrace();
		StackTraceElement[] st = ex.getStackTrace();
		
		for (StackTraceElement stackTraceElement : st) {
			String exclass = stackTraceElement.getClassName();
			String method = stackTraceElement.getMethodName();
			
			ErrorLog log = new ErrorLog();
			log.setErrorTime(new Date());
			log.setErrorClass(exclass);
			log.setErrorMethod(method);
			log.setContent(ex.getMessage()!=null&&ex.getMessage().length()>500?ex.getMessage().substring(0,500):ex.getMessage());
			logService.addErrorLog(log);
			break;
		}

		BaseRes bas =null;
		if(ex.getMessage().contains("exceeds the configured maximum")&&ex.getClass().getName().contains("MultipartException")) {
			bas=new BaseRes(false,BaseRes.FAILURE_CODE, "上传文件过大！");
		}
		else if(ex.getMessage().contains("重新登录")){
			bas=new BaseRes(false,BaseRes.LOGIN_CODE,"请重新登录！");
		}
		else {
			bas=new BaseRes(false,BaseRes.FAILURE_CODE,"操作失败！");
		}
		try{
			//写入错误日志文件
			String origin = request.getHeader("Origin");
			BaseRes.setResponse(origin,response);
			response.getWriter().write(JSONObject.toJSONString(bas));
		}catch(Exception e){
		}
	}

}

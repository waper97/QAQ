package com.yj.hqbz.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yj.hqbz.model.system.SystemParams;
import com.yj.hqbz.services.system.SystemParamService;
/**
 * 系统参数修改类
 * @author Win7
 *
 */
@Controller
public class SysParamsController {

	@Resource
	SystemParamService paramService;
	
	
	@RequestMapping("/system/getParams")
	public String getSysParams(HttpServletRequest request){
		request.setAttribute("sysParamsMap", Global.getSystemInfo());
		return "sysParams";
	}
	
	@RequestMapping("/system/reset")
	@ResponseBody
	public String reset(){
		List<SystemParams> paramList = paramService.listAllParams();  //取得所有的参数进行处理
		Map<String,Object> paramMap = new HashMap<String,Object>();
		for(SystemParams param:paramList){
			paramMap.put(param.getParamCode(), param.getValue());
		}
		Global.setSystemInfo(paramMap);		
		return "true";
	}
}

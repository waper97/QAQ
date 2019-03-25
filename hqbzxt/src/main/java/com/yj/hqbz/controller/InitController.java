package com.yj.hqbz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.yj.hqbz.model.system.SystemParams;
import com.yj.hqbz.services.system.SystemParamService;
import com.yj.hqbz.services.system.SystemRoleAndResService;
import com.yj.hqbz.web.Global;

// 用于启动tomcat时，初始化数据。执行的特殊类
@Controller
public class InitController {
	@Resource
	SystemParamService paramService;
	@Resource
	SystemRoleAndResService resService;
	
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void contextInitialized() {
		System.out.println("进行系统初始化操作");	
		//取系统参数
		initSystemConfigs(paramService);
		
		initSystemResources();
		System.out.println("完成系统初始化");
	}
	
	// 初始化系统参数表
	private void initSystemConfigs(SystemParamService service){
		List<SystemParams> paramList = service.listAllParams();  
		Map<String,Object> paramMap = new HashMap<String,Object>();
		for(SystemParams param:paramList){
			paramMap.put(param.getParamCode(), param.getValue());
		}
		Global.setSystemInfo(paramMap);			
	}
	
	private void initSystemResources(){
		Global.setResourceList(resService.getAllResource());
	}
}

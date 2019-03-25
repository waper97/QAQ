package com.yj.hqbz.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.hqbz.model.system.SystemParams;
import com.yj.hqbz.services.system.SystemParamService;
import com.yj.hqbz.services.system.SystemRoleAndResService;
import com.yj.hqbz.web.Global;

/**  
 * @Title: SystemParamConfigController.java
 * @Package com.yj.hqbz.controller.system
 * @Description: TODO
 * @author xx
 * @date 2019-2-20
 */
@RestController
@RequestMapping("/params/manage")
public class SystemParamConfigController extends BaseController{

	@Resource
	SystemParamService paramService;
	@Resource
	SystemRoleAndResService authService;
	
	@GetMapping("reload")
	public Object reloadParams(){
		List<SystemParams> paramList = paramService.listAllParams();  
		Map<String,Object> paramMap = new HashMap<String,Object>();
		for(SystemParams param:paramList){
			paramMap.put(param.getParamCode(), param.getValue());
		}
		Global.setSystemInfo(paramMap);	
		
		Global.setResourceList(authService.getAllResource());
		
		return success("同步完成！");
	}
	
	@GetMapping("list")
	public Object listParams(DataGridModel grid){
		PageInfo<SystemParams> pg = paramService.listParams(grid.getPage(), grid.getRows());
		return new BaseRes("success",pg.getTotal(),pg.getPages(),pg.getList());
	}
	
	
	@PostMapping("save")
	public Object save(SystemParams param){
		if(param.getId() == null){
			if(paramService.getParamByCode(param.getParamCode())!=null){
				return fail("该参数编码已存在，请重新输入！");
			}
		}
		paramService.insertOrUpdateParam(param);
		return success("保存成功！");
	}
	
	
	@PostMapping("delete")
	public Object deleteParam(Integer id){
		if(id == null){
			return fail("参数非法！");
		}
		else{
			SystemParams param = new SystemParams();
			param.setId(id);
			paramService.deleteParam(param);
			return success("删除成功！");
		}
	}
	

}

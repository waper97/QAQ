package com.yj.hqbz.controller.schoolOperation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.hqbz.model.schoolOperation.FoodAdditive;
import com.yj.hqbz.model.schoolOperation.FoodAdditiveOut;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.schoolOperation.FoodAdditiveService;
import com.yj.hqbz.util.DateUtil;
import com.yj.hqbz.util.StringUtil;
import com.yj.hqbz.web.Global;

/**  
 * @Title: FoodAdditiveController.java
 * @Package com.yj.hqbz.controller.schoolOperation
 * @Description: TODO
 * @author xx
 * @date 2019-3-16
 */
@RestController
public class FoodAdditiveController extends BaseController{

	@Resource
	FoodAdditiveService service;
	
	/**
	 * 获取食品添加剂使用列表
	 * @param param
	 * @param model
	 * @return
	 */
	@GetMapping("/foodAddtive/school/getList")
	public Object getFoodAdditiveList(@RequestParam Map<String,Object> param,DataGridModel model){
		param.put("orgid", getTokenUser().getOrgId());
		PageInfo<FoodAdditive> pg = service.selectFoodAdditiveListByParam(param,model.getPage(),model.getRows());
		return new BaseRes("Ok",pg.getTotal(),pg.getPages(),pg.getList());
	}
	
	//添加剂出库记录
	@GetMapping("/foodAddtive/school/getOutDetailList")
	public Object getFoodAdditiveListOutList(){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orgid", this.getTokenUser().getOrgId());
		param.put("goodsTypeid", Global.getSystemParamValueByKey("additive_typeid"));
		List<FoodAdditiveOut> list = service.getFoodAdditiveOutListByParam(param);
		return success(list);
	}
	
	/**
	 * 提交食品添加剂使用记录
	 * @param record
	 * @return
	 */
	@PostMapping("/foodAddtive/school/saveRecord")
	public Object insertFoodAdditiveUseRecord(FoodAdditive record){
		if(StringUtil.isBlank(record.getDetailid())
				|| record.getUseDate()==null
				|| record.getMenuType() == null
//				|| StringUtil.isBlank(record.getAliasName())   责任人是用aliasName还是用personAilable
				|| StringUtil.isBlank(record.getPersonAliable())
				|| record.getQty().compareTo(new BigDecimal(0))<=0
				|| record.getPercent().compareTo(new BigDecimal(0))<=0)
			return fail("参数非法");
		else{
			UserInfo user = this.getTokenUser();
			record.setCreator(user.getUserName());
			record.setCreateDate(new Date());
			record.setOrgid(user.getOrgId());
			record.setStatus(0);
			try{
				service.insertFoodAdditive(record);
				return success("保存成功");
			}
			catch(Exception ex){
				return fail("保存失败");
			}
		}
	}
	
	/**
	 * 修改保存
	 * @param record
	 * @return
	 */
	@PostMapping("/foodAddtive/school/updateRecord")
	public Object updateFoodAdditiveUseRecord(FoodAdditive record){
		if(StringUtil.isBlank(record.getId())
				|| record.getUseDate()==null
				|| record.getMenuType() == null
				|| StringUtil.isBlank(record.getAliableid())
				|| StringUtil.isBlank(record.getPersonAliable())
				|| record.getQty().compareTo(new BigDecimal(0))<=0
				|| record.getPercent().compareTo(new BigDecimal(0))<=0)
			return fail("参数非法");
		else{
			UserInfo user = getTokenUser();
			
			FoodAdditive _additive = service.selectFoodAdditiveDetailInfo(record.getId());
			if(_additive == null){
				return fail("参数非法");
			}
			else{
				_additive.setLastOpUser(user.getUserName());
				_additive.setLastOpDate(new Date());
				_additive.setUseDate(record.getUseDate());
				_additive.setMenuType(record.getMenuType());
				_additive.setQty(record.getQty());
				_additive.setPercent(record.getPercent());
				_additive.setAliableid(record.getAliableid());
				_additive.setAliasName(record.getPersonAliable());
				_additive.setRemark(record.getRemark());
				try{
					service.updateFoodAdditive(_additive);
					return success("保存成功");
				}
				catch(Exception ex){
					ex.printStackTrace();
					return fail("保存失败");
				}
			}			
		}
	}
	
	/**
	 * 删除使用记录
	 * @param record
	 * @return
	 */
	@PostMapping("/foodAddtive/school/deleteRecord")
	public Object removeFoodAdditiveRecord(String id){
		if(StringUtil.isBlank(id))
			return fail("参数非法");
		else{
			UserInfo user = getTokenUser();
			
			FoodAdditive _additive = service.selectFoodAdditiveDetailInfo(id);
			if(_additive == null || _additive.getOrgid().intValue()!=user.getOrgId().intValue()){
				return fail("参数非法");
			}
			else{
				service.deleteFoodAdditive(id);
				return success("删除成功");
			}			
		}
	}
	
	/**
	 * 查看详情
	 * @param id
	 * @return
	 */
	@GetMapping("/foodAddtive/school/getDetailInfo")
	public Object getFoodAdditiveDetailInfo(String id){
		if(StringUtil.isBlank(id))
			return fail("参数非法");
		else{
			UserInfo user = getTokenUser();
			
			FoodAdditive _additive = service.selectFoodAdditiveDetailInfo(id);
			if(_additive == null || _additive.getOrgid().intValue()!=user.getOrgId().intValue()){
				return fail("参数非法");
			}
			else{
				return success(_additive);
			}			
		}
	}

}
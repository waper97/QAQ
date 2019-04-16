package com.yj.hqbz.controller.inventory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.hqbz.model.sporadic.SporadicDetail;
import com.yj.hqbz.model.sporadic.SporadicGoods;
import com.yj.hqbz.model.sporadic.SporadicIndex;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.inventory.SporadicService;
import com.yj.hqbz.util.DateUtil;
import com.yj.hqbz.util.StringUtil;

@RestController
@RequestMapping("/inventory/sporadic")
public class SporadicController extends BaseController{

	@Resource
	private SporadicService sporadicService;
	
	
	@RequestMapping("/getInStockBySporadic")
	public Object getInStockBySporadic(DataGridModel model) {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("orgid", getTokenUser().getOrgId());
		PageInfo<Map<String,Object>> info = sporadicService.getInStockBySporadic(param, model.getPage(), model.getRows());
		return new BaseRes("获取成功！", info.getTotal(), info.getPages(), info.getList());
	}
	
	
	@RequestMapping("/getSporadicList")
	public Object getSporadicList(String goodsName,String purchaser,Integer status,Integer goodsTypeid,DataGridModel model) {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("orgid", getTokenUser().getOrgId());
		param.put("goodsName", goodsName);
		param.put("purchaser", purchaser);
		param.put("status", status);
		param.put("goodsTypeid", goodsTypeid);
		param.put("orderBy", model.getOrderBy());
		param.put("orderType", model.getOrderType());
		PageInfo<SporadicDetail> info = sporadicService.getSporadicList(param, model.getPage(), model.getRows());
		return new BaseRes("获取成功！", info.getTotal(), info.getPages(), info.getList());
	}
	
	@RequestMapping("/getSporadicDetail")
	public Object getSporadicDetail(String detailid) {
		if(StringUtil.isBlank(detailid)) {
			return fail("明细ID不能为空！");
		}
		return success(sporadicService.getSporadicDetail(detailid));
	}
	
	@RequestMapping("/addSporadic")
	public Object addSporadic(@RequestBody SporadicDetail sporadic) {
		if(sporadic.getPurchaserid()==null||StringUtil.isBlank(sporadic.getPurchaser())||sporadic.getBuyDate()==null||StringUtil.isBlank(sporadic.getSupplier())) {
			return fail("采购人、采购时间、采购来源不能为空！");
		}
		if(sporadic.getGoodsTypeid()==null||StringUtil.isBlank(sporadic.getGoodsName())||StringUtil.isBlank(sporadic.getUnit())||sporadic.getQty()==null||sporadic.getPrice()==null) {
			return fail("商品名称、单位、商品类别、单价、数量不能为空！");
		}
		if(sporadic.getPutInWarehouse()==null) {
			return fail("请选择是否需要入库！");
		}
		if(sporadic.getPutInWarehouse()==1&&sporadic.getUseDate()==null) {
			return fail("使用时间不能为空！");
		}
		if(sporadic.getProDate()==null||sporadic.getUselifeDate()==null||sporadic.getIntervalDate()==null) {
			return fail("生产日期、近效期、保质期不能为空！");
		}
		SporadicIndex index = setIndexByAdd(sporadic,getTokenUser());
		SporadicGoods goods = setGoodsByAdd(sporadic,getTokenUser());
		sporadicService.addSporadic(index,goods,sporadic);
		//日志
		saveJournalLog("新增零星采购单【"+index.getVoucherCode()+"】", "id:"+index.getId());
		return success("添加成功！");
	}
	
	private SporadicIndex setIndexByAdd(SporadicDetail sporadic,UserInfo user) {
		SporadicIndex index=new SporadicIndex();
		index.setId(StringUtil.getUUID());
		index.setVoucherCode("SP"+DateUtil.getStrByDate(new Date(), "yyyyMMddHHmmss")+StringUtil.getRandomNum(100));
		index.setOrgid(user.getOrgId());
		index.setTotal(sporadic.getQty().multiply(sporadic.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP));
		index.setPurchaserid(sporadic.getPurchaserid());
		index.setPurchaser(sporadic.getPurchaser());
		index.setUseDate(sporadic.getUseDate());
		index.setPutInWarehouse(sporadic.getPutInWarehouse());
		index.setStatus(0);
		index.setRemark(sporadic.getRemark());
		index.setDeleteStatus(0);
		index.setCreator(user.getTrueName());
		index.setCreateDate(new Date());
		index.setLastOpUser(index.getCreator());
		index.setLastOpDate(index.getCreateDate());
		return index;
	}
	
	private SporadicGoods setGoodsByAdd(SporadicDetail sporadic,UserInfo user) {
		SporadicGoods goods=new SporadicGoods();
		goods.setGoodsid(StringUtil.getUUID());
		goods.setCode("SG");
		goods.setGoodsTypeid(sporadic.getGoodsTypeid());
		goods.setGoodsName(sporadic.getGoodsName());
		goods.setNamepy(getPinYin(null, goods.getGoodsName()));
		goods.setUnit(sporadic.getUnit());
		goods.setOrgid(user.getOrgId());
		goods.setCreator(user.getTrueName());
		goods.setCreateDate(new Date());
		goods.setLastOpUser(goods.getCreator());
		goods.setLastOpDate(goods.getCreateDate());
		return goods;
	}
	
	@RequestMapping("/updateSporadic")
	public Object updateSporadic(@RequestBody SporadicDetail sporadic) {
		if(sporadic.getIndexid()==null) {
			return fail("单据ID不能为空！");
		}
		if(sporadic.getPurchaserid()==null||StringUtil.isBlank(sporadic.getPurchaser())||sporadic.getBuyDate()==null||StringUtil.isBlank(sporadic.getSupplier())) {
			return fail("采购人、采购时间、采购来源不能为空！");
		}
		if(sporadic.getGoodsTypeid()==null||StringUtil.isBlank(sporadic.getGoodsName())||StringUtil.isBlank(sporadic.getUnit())||sporadic.getQty()==null||sporadic.getPrice()==null) {
			return fail("商品名称、单位、商品类别、单价、数量不能为空！");
		}
		if(sporadic.getPutInWarehouse()==null) {
			return fail("请选择是否需要入库！");
		}
		if(sporadic.getPutInWarehouse()==1&&sporadic.getUseDate()==null) {
			return fail("使用时间不能为空！");
		}
		if(sporadic.getProDate()==null||sporadic.getUselifeDate()==null||sporadic.getIntervalDate()==null) {
			return fail("生产日期、近效期、保质期不能为空！");
		}
		SporadicIndex oldIndex = sporadicService.getIndexByPrimaryKey(sporadic.getIndexid());
		if(oldIndex==null) {
			return fail("单据不存在，无法修改！");
		}
		if(oldIndex.getDeleteStatus()==1) {
			return fail("单据已删除，无法修改！");
		}
		if(oldIndex.getStatus()==1) {
			return fail("单据已入库，不允许修改！");
		}
		
		setIndexByUpdate(oldIndex,sporadic,getTokenUser());
		SporadicGoods goods = setGoodsByUpdate(sporadic,getTokenUser());
		sporadicService.updateSporadic(oldIndex,goods,sporadic);
		//日志
		saveJournalLog("修改零星采购单【"+oldIndex.getVoucherCode()+"】", "id:"+oldIndex.getId());
		return success("修改成功！");
	}
	
	private void setIndexByUpdate(SporadicIndex oldIndex,SporadicDetail sporadic,UserInfo user) {
		oldIndex.setTotal(sporadic.getQty().multiply(sporadic.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP));
		oldIndex.setPurchaserid(sporadic.getPurchaserid());
		oldIndex.setPurchaser(sporadic.getPurchaser());
		oldIndex.setUseDate(sporadic.getUseDate());
		oldIndex.setPutInWarehouse(sporadic.getPutInWarehouse());
		oldIndex.setRemark(sporadic.getRemark());
		oldIndex.setLastOpUser(user.getTrueName());
		oldIndex.setLastOpDate(new Date());
	}
	
	private SporadicGoods setGoodsByUpdate(SporadicDetail sporadic,UserInfo user) {
		SporadicGoods goods=sporadicService.getGoodsByIndexid(sporadic.getIndexid());
		goods.setGoodsTypeid(sporadic.getGoodsTypeid());
		goods.setGoodsName(sporadic.getGoodsName());
		goods.setNamepy(getPinYin(null, goods.getGoodsName()));
		goods.setUnit(sporadic.getUnit());
		goods.setLastOpUser(user.getTrueName());
		goods.setLastOpDate(new Date());
		return goods;
	}
	
	
	@RequestMapping("/deleteSporadic")
	public Object deleteSporadic(String indexid) {
		if(StringUtil.isBlank(indexid)) {
			return fail("明细ID不能为空！");
		}
		SporadicIndex index = sporadicService.getIndexByPrimaryKey(indexid);
		if(index==null) {
			return fail("单据不存在，无法删除！");
		}
		if(index.getStatus()==1) {
			return fail("单据已入库，不允许删除！");
		}
		if(index.getDeleteStatus()==1) {
			return fail("单据已删除，不允许重复操作！");
		}
		
		index.setDeleteStatus(1);
		index.setLastOpUser(getTokenUser().getTrueName());
		index.setLastOpDate(new Date());
		sporadicService.deleteSporadic(index);
		//日志
		saveJournalLog("删除零星采购单【"+index.getVoucherCode()+"】", "id:"+index.getId());
		return success("删除成功！"); 
	}
}
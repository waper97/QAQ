package com.yj.hqbz.controller.inventory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.hqbz.model.inventory.BVoucherIndex;
import com.yj.hqbz.model.inventory.Inventory;
import com.yj.hqbz.model.order.OrderDetail;
import com.yj.hqbz.model.order.OrderForm;
import com.yj.hqbz.model.sporadic.SporadicIndex;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.goods.GoodsService;
import com.yj.hqbz.services.inventory.InventoryService;
import com.yj.hqbz.services.inventory.SporadicService;
import com.yj.hqbz.services.order.OrderFormService;
import com.yj.hqbz.util.DateUtil;
import com.yj.hqbz.util.StringUtil;

@RestController
@RequestMapping("/inventory")
public class InventoryController extends BaseController{

	@Resource
	private InventoryService inventoryService;
	@Resource
	private GoodsService goodsService;
	@Resource
	OrderFormService orderService;
	@Resource
	SporadicService sporadicService;
	
	@RequestMapping("/order/getOrderBill")
	public Object getOrderBill(DataGridModel model) {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("orgid", getTokenUser().getOrgId());
		PageInfo<OrderForm> info = inventoryService.getNotInStoreOrderBill(param, model.getPage(), model.getRows());	
		return new BaseRes("获取成功！", info.getTotal(), info.getPages(), info.getList());
	}
	
	@RequestMapping("/order/getOrderDetail")
	public Object getOrderDetail(String orderid,DataGridModel model) {
		if(orderid==null) {
			return fail("订单ID不能为空！");
		}
		PageInfo<Map<String, Object>> info = inventoryService.getOrderDetail(orderid, model.getPage(), model.getRows());
		return new BaseRes("获取成功！", info.getTotal(), info.getPages(), info.getList());
	}
	
	@RequestMapping("/stock/getStockBill")
	public Object getStockBill(String goodsName,String warehouse,Integer haveQty,Integer isInterval,DataGridModel model) {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("orgid", getTokenUser().getOrgId());
		param.put("goodsName", goodsName);
		param.put("warehouse", warehouse);
		param.put("haveQty", haveQty);
		param.put("isInterval", isInterval);
		String orderBy = model.getOrderBy();
		param.put("orderBy", StringUtil.isBlank(orderBy)?"traceNo":orderBy);
		param.put("orderType", model.getOrderType());
		PageInfo<Inventory> info = inventoryService.getStockBill(param, model.getPage(), model.getRows());	
		return new BaseRes("获取成功！", info.getTotal(), info.getPages(), info.getList());
	}
	
	@RequestMapping("/stock/getStockBatch")
	public Object getStockBatch(String goodsid,DataGridModel model) {
		if(StringUtil.isBlank(goodsid)) {
			return fail("商品ID不能为空！");
		}
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("orgid", getTokenUser().getOrgId());
		param.put("goodsid", goodsid);
		param.put("orderBy", model.getOrderBy());
		param.put("orderType", model.getOrderType());
		PageInfo<Inventory> info = inventoryService.getStockBatch(param, model.getPage(), model.getRows());	
		return new BaseRes("获取成功！", info.getTotal(), info.getPages(), info.getList());
	}
	
	@RequestMapping("/stock/addInStock")
	public Object addInventory(@RequestBody BVoucherIndex index) {
		if(index.getStocks()==null||index.getStocks().size()==0) {
			return fail("明细不能为空！");
		}
		//设置买家出入库主表
		for(Inventory inv:index.getStocks()){  //增加是否重复入库的检验（包括采购入库和零星入库）
			//根据trace id获取订单明细
			Object obj = checkInStockValid(inv);
			if(obj != null){
				return obj;
			}			
		}
		
		UserInfo user = getTokenUser();
		index.setId(StringUtil.getUUID());
		index.setOrgid(user.getOrgId());
		index.setBusinessDate(new Date());
		index.setLiablePersonid(user.getUserid());
		index.setLiablePerson(user.getTrueName());
		index.setDealerid(user.getUserid());
		index.setDealer(user.getTrueName());
		index.setDealDate(index.getBusinessDate());
		index.setCreator(user.getTrueName());
		index.setCreateDate(index.getBusinessDate());
		index.setLastOpUser(user.getTrueName());
		index.setLastOpDate(index.getBusinessDate());
		index.setStatus(0);
		inventoryService.addInventory(index.getStocks(),index);
		//日志
		saveJournalLog("入库商品", "bVchid:"+index.getId());
		return success("入库成功！");
	}
	
	/**
	 * 检查入库是否合法
	 * @param inv
	 * @return
	 */
	private Object checkInStockValid(Inventory inv){
		if(inv.getInType().intValue()==0){  //采购入库
			OrderDetail detail = orderService.getOrderDetailInfoByTraceId(inv.getTraceid());
			if(detail == null){
				return fail("参数非法！");
			}
			else if(detail.getInstoreStatus()!=null && detail.getInstoreStatus().intValue()==1){
				return fail("不允许重复入库！");
			}
			else
				return null;
		}
		else if(inv.getInType().intValue()==0){ //零星入库
			SporadicIndex idx = sporadicService.getSporadicIndexByTraceId(inv.getTraceid());
			if(idx == null){
				return fail("参数非法");
			}
			else if(idx.getStatus()!=null && idx.getStatus().intValue()==1){
				return fail("不允许重复入库");
			}
			else{
				return null;
			}
		}
		else 
			return null;
	}
	
	@RequestMapping("/stock/outStock")
	public Object outStock(@RequestBody BVoucherIndex index) {
		if(index.getStocks()==null||index.getStocks().size()==0) {
			return fail("库存出库明细不能为空！");
		}
		if(index.getBusinessDate()==null||index.getUseDate()==null||index.getPurpose()==null) {
			return fail("出库日期、用途、使用日期不能为空！");
		}
		if(StringUtil.isBlank(index.getLiablePersonid())||StringUtil.isBlank(index.getLiablePerson())) {
			return fail("责任人不能为空！");
		}
//		if(StringUtil.isBlank(index.getDealerid())||StringUtil.isBlank(index.getDealer())||index.getDealDate()==null) {
//			return fail("处理人、处理时间不能为空！");
//		}
			
		for (Inventory inventory : index.getStocks()) {
			Inventory stock = inventoryService.selectByPrimaryKey(inventory.getId());
			if(stock.getQty().compareTo(inventory.getQty())<0) {
				return fail("批次号为【"+stock.getTraceNo()+"】的商品【"+stock.getGoodsName()+"】库存数量不足！");
			}
		}
		
		//设置买家出入库主表
		UserInfo user = getTokenUser();
		index.setId(StringUtil.getUUID());
		index.setOrgid(user.getOrgId());
		index.setCreator(user.getTrueName());
		index.setCreateDate(new Date());
		index.setLastOpUser(index.getCreator());
		index.setLastOpDate(index.getCreateDate());
		index.setStatus(0);
		inventoryService.outStock(index);
		//日志
		saveJournalLog("出库商品", "bVchid:"+index.getId());
		return success("出库成功！");
	}
	
	@RequestMapping("/stock/getOutInDetail")
	public Object getOutInDetail(String id,DataGridModel model) {
		if(StringUtil.isBlank(id)) {
			return fail("库存ID不能为空！");
		}
		PageInfo<Map<String,Object>> info = inventoryService.getOutInDetail(id, model.getPage(), model.getRows());	
		return new BaseRes("获取成功！", info.getTotal(), info.getPages(), info.getList());
	}
	
}

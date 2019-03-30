package com.yj.hqbz.services.impl.inventory;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.inventory.BVoucherDetailMapper;
import com.yj.hqbz.mapper.inventory.BVoucherIndexMapper;
import com.yj.hqbz.mapper.inventory.InventoryMapper;
import com.yj.hqbz.mapper.trace.GoodsTraceMapper;
import com.yj.hqbz.model.inventory.BVoucherDetail;
import com.yj.hqbz.model.inventory.BVoucherIndex;
import com.yj.hqbz.model.inventory.Inventory;
import com.yj.hqbz.model.order.OrderForm;
import com.yj.hqbz.model.trace.GoodsTrace;
import com.yj.hqbz.services.inventory.InventoryService;
import com.yj.hqbz.util.DateUtil;
import com.yj.hqbz.util.StringUtil;
@Service
public class InventoryServiceImpl implements InventoryService{

	@Resource
	private InventoryMapper inventoryMapper;
	@Resource
	private BVoucherIndexMapper bVchIndexMapper;
	@Resource
	private BVoucherDetailMapper bvchDetailMapper;
	@Resource
	private GoodsTraceMapper goodsTraceMapper;

	public Inventory selectByPrimaryKey(String id) {
		return inventoryMapper.selectByPrimaryKey(id);
	}
	
	public Inventory getStockById(String id) {
		return inventoryMapper.getStockById(id);
	}
	
	public PageInfo<OrderForm> getNotInStoreOrderBill(Map<String, Object> param, int page, int rows) {
		PageHelper.startPage(page, rows);
		List<OrderForm> list = inventoryMapper.getNotInStoreOrderBill(param);
		PageInfo<OrderForm> info=new PageInfo<OrderForm>(list);
		return info;
	}

	public PageInfo<Map<String,Object>> getOrderDetail(String orderid,int page,int rows) {
		PageHelper.startPage(page, rows);
		List<Map<String,Object>> list = inventoryMapper.getOrderDetail(orderid);
		PageInfo<Map<String,Object>> info=new PageInfo<Map<String,Object>>(list);
		return info;
	}
	
	public PageInfo<Inventory> getStockBill(Map<String, Object> param, int page, int rows) {
		PageHelper.startPage(page, rows);
		List<Inventory> list = inventoryMapper.getStockBill(param);
		PageInfo<Inventory> info=new PageInfo<Inventory>(list);
		return info;
	}
	
	public PageInfo<Inventory> getStockBatch(Map<String, Object> param, int page, int rows) {
		PageHelper.startPage(page, rows);
		List<Inventory> list = inventoryMapper.getStockBatch(param);
		PageInfo<Inventory> info=new PageInfo<Inventory>(list);
		return info;
	}
	
	@Transactional
	public void addInventory(List<Inventory> stocks,BVoucherIndex index) {
		for (Inventory stock:stocks) {
			if(StringUtil.isBlank(stock.getTraceid())||StringUtil.isBlank(stock.getWarehouse())||stock.getInType()==null) {
				throw new RuntimeException("溯源ID、库存位置、入库类型不能为空！");
			}
			//保存库存
			stock.setId(StringUtil.getUUID());
			stock.setOrgid(index.getOrgid());
			stock.setInDate(index.getBusinessDate());
			
			if(stock.getInType()==0) {
				int count = inventoryMapper.addInventoryByOrder(stock);
				if(count>0) {
					//添加买家入库信息
					addBVoucherByIn(stocks.indexOf(stock),stock.getId(),index);
					//修改入库时间
					updateInStockDate(stock.getTraceid());
					
				}
			}else if(stock.getInType()==1){
				int count = inventoryMapper.addInventoryBySporadic(stock);
				if(count>0) {
					//添加买家入库信息
					addBVoucherByIn(stocks.indexOf(stock),stock.getId(),index);
					//修改入库时间
					updateInStockDate(stock.getTraceid());
					
				}
			}
			
		}
	}
	
	private void updateInStockDate(String traceid) {
		GoodsTrace trace = goodsTraceMapper.selectByPrimaryKey(traceid);
		if(trace.getTraceType()==0) {
			//订单
			inventoryMapper.updateOrderInStockDate(trace.getOrderDetailid());
		}
		if(trace.getTraceType()==1) {
			//零星采购
			inventoryMapper.updateSporadicInStockDate(trace.getOrderid());
		}
	}
	
	
	@Transactional
	public void outStock(BVoucherIndex index) {
		List<Inventory> stocks = index.getStocks();
		for (Inventory stock : stocks) {
			int count = inventoryMapper.outStock(stock);
			if(count>0) {
				//添加买家入库信息
				addBVoucherByOut(stocks.indexOf(stock),stock,index);
			}
		}
	}
	
	
	//添加买家入库信息
	private void addBVoucherByIn(int i,String stockid,BVoucherIndex index) {
		//保存买家入库主表
		if(i==0) {
			index.setVoucherType(0);
			index.setVoucherCode(DateUtil.getStrByDate(new Date(), "yyyyMMddHHmmss")+index.getVoucherType()+StringUtil.getRandomNum(1000));
			bVchIndexMapper.addBVoucherIndex(index);
		}
		//设置买家入库明细
		BVoucherDetail detail=new BVoucherDetail();
		detail.setId(StringUtil.getUUID());
		detail.setIndexid(index.getId());
		detail.setStockid(stockid);
		//保存买家入库明细
		bvchDetailMapper.addBVoucherDetailByIn(detail);
	}
	
	//添加买家出库信息
	private void addBVoucherByOut(int i,Inventory stock,BVoucherIndex index) {
		//保存买家出入库主表
		if(i==0) {
			index.setVoucherType(1);
			index.setVoucherCode(DateUtil.getStrByDate(new Date(), "yyyyMMddHHmmss")+index.getVoucherType()+StringUtil.getRandomNum(1000));
			bVchIndexMapper.addBVoucherIndex(index);
		}
		//设置买家出入库明细
		BVoucherDetail detail=new BVoucherDetail();
		detail.setId(StringUtil.getUUID());
		detail.setIndexid(index.getId());
		detail.setStockid(stock.getId());
		detail.setQty(stock.getQty());
		//保存买家出入库明细
		bvchDetailMapper.addBVoucherDetailByOut(detail);
	}
	
	
	public PageInfo<Map<String, Object>> getOutInDetail(String id, int page, int rows) {
		PageHelper.startPage(page, rows);
		List<Map<String, Object>> list = bvchDetailMapper.getOutInDetail(id);
		PageInfo<Map<String, Object>> info=new PageInfo<Map<String, Object>>(list);
		return info;
	}
}

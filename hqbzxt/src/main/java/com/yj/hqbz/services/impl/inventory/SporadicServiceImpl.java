package com.yj.hqbz.services.impl.inventory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.sporadic.SporadicDetailMapper;
import com.yj.hqbz.mapper.sporadic.SporadicGoodsMapper;
import com.yj.hqbz.mapper.sporadic.SporadicIndexMapper;
import com.yj.hqbz.mapper.trace.GoodsTraceMapper;
import com.yj.hqbz.mapper.trace.GoodsTracePicMapper;
import com.yj.hqbz.model.sporadic.SporadicDetail;
import com.yj.hqbz.model.sporadic.SporadicGoods;
import com.yj.hqbz.model.sporadic.SporadicIndex;
import com.yj.hqbz.model.trace.GoodsTrace;
import com.yj.hqbz.model.trace.GoodsTracePic;
import com.yj.hqbz.services.inventory.SporadicService;
import com.yj.hqbz.util.DateUtil;
import com.yj.hqbz.util.StringUtil;
@Service
public class SporadicServiceImpl implements SporadicService{
	
	@Resource
	private SporadicIndexMapper sporadicIndexMapper;
	@Resource
	private SporadicDetailMapper sporadicDetailMapper;
	@Resource
	private SporadicGoodsMapper sporadicGoodsMapper;
	@Resource
	private GoodsTraceMapper goodsTraceMapper;
	@Resource
	private GoodsTracePicMapper goodsTracePicMapper;

	public SporadicIndex getIndexByPrimaryKey(String id) {
		return sporadicIndexMapper.selectByPrimaryKey(id);
	}
	
	public PageInfo<Map<String, Object>> getInStockBySporadic(Map<String, Object> param, int page, int rows) {
		PageHelper.startPage(page, rows);
		List<Map<String,Object>> list = sporadicIndexMapper.getInStockBySporadic(param);
		PageInfo<Map<String,Object>> info=new PageInfo<Map<String,Object>>(list);
		return info;
	}

	public PageInfo<SporadicDetail> getSporadicList(Map<String, Object> param, int page, int rows) {
		PageHelper.startPage(page, rows);
		List<SporadicDetail> list = sporadicDetailMapper.getSporadicList(param);
		PageInfo<SporadicDetail> info=new PageInfo<SporadicDetail>(list);
		return info;
	}

	
	public SporadicDetail getSporadicDetail(String detailid) {
		SporadicDetail detail = sporadicDetailMapper.getSporadicDetail(detailid);
		if(detail!=null) {
			detail.setGoodsPic(goodsTracePicMapper.getGoodsPicByTraceid(detail.getTraceid()));
		}
		return detail;
	}
	
	@Transactional
	public void addSporadic(SporadicIndex index, SporadicGoods goods, SporadicDetail sporadic) {
		//新增主表
		sporadicIndexMapper.addIndex(index);
		//新增商品
		sporadicGoodsMapper.addGoods(goods);
		//新增子表
		sporadic.setDetailid(StringUtil.getUUID());
		sporadic.setIndexid(index.getId());
		sporadic.setGoodsid(goods.getGoodsid());
		sporadic.setTotal(index.getTotal());
		sporadicDetailMapper.addDetail(sporadic);
		//新增溯源信息
		GoodsTrace trace = setGoodsTraceByAdd(sporadic);
		goodsTraceMapper.addTrace(trace);
		//新增溯源图片
		addTracePic(sporadic,trace);
	}
	
	private GoodsTrace setGoodsTraceByAdd(SporadicDetail sporadic) {
		GoodsTrace trace=new GoodsTrace();
		trace.setId(StringUtil.getUUID());
		trace.setOrderid(sporadic.getIndexid());
		trace.setOrderDetailid(sporadic.getDetailid());
		trace.setTraceType(1);
		trace.setGoodsid(sporadic.getGoodsid());
		trace.setAddDate(new Date());
		trace.setBatchNo(DateUtil.getStrByDate(new Date(), "yyyyMMddHHmmss")+StringUtil.getRandomNum(1000));
		trace.setSortQty(sporadic.getQty());
		trace.setSupplier(sporadic.getSupplier());
		trace.setProDate(sporadic.getProDate());
		trace.setIntervalDate(sporadic.getIntervalDate());
		trace.setUselifeDate(sporadic.getUselifeDate());
		trace.setBuyDate(trace.getBuyDate());
		return trace;
	}
	
	public SporadicGoods getGoodsByIndexid(String indexid) {
		return sporadicGoodsMapper.getGoodsByIndexid(indexid);
	}

	@Transactional
	public void updateSporadic(SporadicIndex index, SporadicGoods goods, SporadicDetail sporadic) {
		//修改主表
		sporadicIndexMapper.updateIndex(index);
		//修改商品
		sporadicGoodsMapper.updateGoods(goods);
		//修改子表
		sporadic.setTotal(index.getTotal());
		sporadicDetailMapper.updateGoodsByIndexid(sporadic);
		//修改溯源信息
		GoodsTrace trace = setGoodsTraceByUpdate(sporadic);
		goodsTraceMapper.updateTrace(trace);
		//先删除后添加溯源图片
		goodsTracePicMapper.deletePicByTraceid(trace.getId());
		addTracePic(sporadic,trace);
	}
	
	private GoodsTrace setGoodsTraceByUpdate(SporadicDetail sporadic) {
		GoodsTrace trace=goodsTraceMapper.getTraceBySporadicid(sporadic.getIndexid());
		trace.setSortQty(sporadic.getQty());
		trace.setSupplier(sporadic.getSupplier());
		trace.setProDate(sporadic.getProDate());
		trace.setIntervalDate(sporadic.getIntervalDate());
		trace.setUselifeDate(sporadic.getUselifeDate());
		trace.setBuyDate(trace.getBuyDate());
		return trace;
	}
	
	private void addTracePic(SporadicDetail sporadic,GoodsTrace trace) {
		List<GoodsTracePic> tracePic = sporadic.getGoodsPic();
		if(tracePic!=null&&tracePic.size()>0) {
			for (GoodsTracePic pic : tracePic) {
				pic.setId(StringUtil.getUUID());
				pic.setTraceid(trace.getId());
				pic.setPicType(2);
				goodsTracePicMapper.addTracePic(pic);
			}
		}
	}
	
	@Transactional
	public int deleteSporadic(SporadicIndex index) {
		return sporadicIndexMapper.deleteSporadic(index);
	}
	
	
}

package com.yj.hqbz.services.impl.goods;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.goods.GoodsMapper;
import com.yj.hqbz.mapper.goods.GoodsPicMapper;
import com.yj.hqbz.mapper.goods.GoodsSKUMapper;
import com.yj.hqbz.mapper.goods.OrgSkuMapper;
import com.yj.hqbz.mapper.org.OrganizationMapper;
import com.yj.hqbz.model.goods.Goods;
import com.yj.hqbz.model.goods.GoodsPic;
import com.yj.hqbz.model.goods.GoodsSKU;
import com.yj.hqbz.model.goods.GoodsSaleInfo;
import com.yj.hqbz.model.goods.OrgSku;
import com.yj.hqbz.model.org.Organization;
import com.yj.hqbz.model.org.Seller;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.goods.OrgSkuService;
import com.yj.hqbz.util.ObjectUtil;
import com.yj.hqbz.util.StringUtil;
@Service
public class OrgSkuServiceImpl implements OrgSkuService{

	@Resource
	GoodsMapper goodsMapper;
	@Resource
	private OrgSkuMapper orgSkuMapper;
	@Resource
	private OrganizationMapper orgMapper;
	@Resource
	private GoodsSKUMapper goodsSKUMapper;
	@Resource
	private GoodsPicMapper goodsPicMapper;
	
	public OrgSku selectByPrimaryKey(Integer id) {
		return orgSkuMapper.selectByPrimaryKey(id);
	}
	
	public PageInfo<OrgSku> getOrgSkuByOrg(Integer orgid, Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		List<OrgSku> list = orgSkuMapper.getOrgSkuByOrg(orgid);
		PageInfo<OrgSku> info=new PageInfo<OrgSku>(list);
		return info;
	}

	public PageInfo<OrgSku> getList(Map<String, Object> map, Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		List<OrgSku> list = orgSkuMapper.getList(map);
		PageInfo<OrgSku> info=new PageInfo<OrgSku>(list);
		return info;
	}
	
	@Transactional
	public int updateStatus(OrgSku sku) {
		return orgSkuMapper.updateStatus(sku);
	}
	@Transactional
	public int deleteOrgSku(OrgSku sku) {
		return orgSkuMapper.deleteOrgSku(sku);
	}
	
	
	public GoodsSaleInfo getGoodsSaleDetailInfo(String goodsid,Integer customerOrgId){		
		Goods goodsItem = goodsMapper.selectByPrimaryKey(goodsid);
		if(goodsItem == null || goodsItem.getStatus()==2){
			return null;
		}
		else{
			GoodsSaleInfo goods = new GoodsSaleInfo();
			try{
				ObjectUtil.fatherToChild(goodsItem, goods);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("goodsid", goodsid);
			param.put("orgid", customerOrgId);
			List<Map<String,Object>> list = orgSkuMapper.selectSellerOrgByCustomerAndGoods(param);
			List<Seller> sellerList = new ArrayList<Seller>();
			
			BigDecimal high = new BigDecimal(0);
			BigDecimal lower = new BigDecimal(0);

			int i = 0;
			
			for(Map<String,Object> map:list){
				Seller seller = new Seller();
				String sellerId = map.get("ORGID").toString();
				String tmp_high = map.get("HIGH").toString();
				String tmp_lower = map.get("LOWER").toString();
				if (i==0){
					high = new BigDecimal(tmp_high);
					lower = new BigDecimal(tmp_lower);
				}
				else{				
					if(new BigDecimal(tmp_high).compareTo(high)>0){
						high = new BigDecimal(tmp_high);
					}
					if(new BigDecimal(tmp_lower).compareTo(lower)<0){
						lower = new BigDecimal(tmp_lower);
					}
				}
				
				
				seller.setOrgid(Integer.parseInt(sellerId));
				seller.setOrgName(map.get("NAME").toString());
				param.put("orgid", sellerId);
				param.put("status", 0);
				seller.setSkuList(orgSkuMapper.getList(param));
				
				//取资质信息
				Organization org = orgMapper.getByPrimaryKey(seller.getOrgid());
				if(org!=null){
					seller.setLicensekeyPic(org.getLicensekeyPic());
					seller.setLicensePic(org.getLicensePic());
					seller.setRatePaying(org.getRatePaying());
					seller.setTradeMark(org.getTradeMark());
				}
				
				sellerList.add(seller);
				++i;
			}
			
			goods.setLower(lower);
			goods.setHigh(high);
			goods.setSeller(sellerList);
			return goods;
		}
	}
	
	@Transactional
	public void insertGoodsSku(Seller seller,UserInfo user){
		List<OrgSku> skuList = seller.getSkuList();
		for(OrgSku sku: skuList){
			Goods g = goodsMapper.selectGoodsInfoBySkuid(sku.getSkuid());
			sku.setOrgid(seller.getOrgid());
			sku.setGoodsTypeid(g.getGoodsTypeid());
			sku.setCostPriceBasic(sku.getCostPrice().divide(sku.getAuixRate(),8,RoundingMode.HALF_UP));
			sku.setCreator(user.getTrueName());
			sku.setSpecInfo(sku.getAuixRate()+sku.getUnit()+"/"+sku.getAuixUnit());
			orgSkuMapper.insertOrgSku(sku);
		}
	}
	
	@Transactional
	public void updateGoodsSku(Seller seller,UserInfo user){
		List<OrgSku> skuList = seller.getSkuList();
		for(OrgSku sku: skuList){
			if(sku.getOrgSkuid()==null){
				Goods g = goodsMapper.selectGoodsInfoBySkuid(sku.getSkuid());
				sku.setOrgid(seller.getOrgid());
				sku.setGoodsTypeid(g.getGoodsTypeid());
				sku.setCostPriceBasic(sku.getCostPrice().divide(sku.getAuixRate(),8,RoundingMode.HALF_UP));
				sku.setCreator(user.getTrueName());
				sku.setSpecInfo(sku.getAuixRate()+sku.getUnit()+"/"+sku.getAuixUnit());
				orgSkuMapper.insertOrgSku(sku);
			}
			else{
			    sku.setSpecInfo(sku.getAuixRate()+sku.getUnit()+"/"+sku.getAuixUnit());
				sku.setCostPriceBasic(sku.getCostPrice().divide(sku.getAuixRate(),8,RoundingMode.HALF_UP));
				sku.setLastOpUser(user.getTrueName());
				orgSkuMapper.updateOrgSku(sku);
			}			
		}
	}

	@Override
	public List<OrgSku> getOrgSkuBySkuId(String skuid) {
		return orgSkuMapper.getOrgSkuBySkuId(skuid);
	}
	
	
	public List<OrgSku> getOrgSkuBySkuIdAndOrgId(String skuid,Integer orgid){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("skuid", skuid);
		param.put("orgid", orgid);
		return orgSkuMapper.getOrgSkuSpecsByParam(param);
	}


	@Override
	public List<OrgSku> getOrgSkuByGoodsId(String goodsid) {
		return orgSkuMapper.getOrgSkuByGoodsId(goodsid);
	}

	@Override
	public Goods getOrgSkuByGoodsidAndOrgid(Map<String, Object> param) {
		Goods goods = goodsMapper.getGoodsByGoodsidAndOrgid(param);
		if(goods==null){
			return null;
		}
		String goodsid = param.get("goodsid").toString();
		String orgid = param.get("orgid").toString();
		List<GoodsSKU> goodsSKUs = goodsSKUMapper.getGoodsSKUByGoodsId(goodsid);
		for (GoodsSKU sku : goodsSKUs) {
			Map<String, Object> param2=new HashMap<>();
			param2.put("goodsid", goodsid);
			param2.put("skuid", sku.getSkuid());
			GoodsPic pic = goodsPicMapper.getGoodsPicByGoodsidAndSkuid(param2);
			sku.setPicUrl(pic.getPicUrl());
			sku.setThumbnailUrl(pic.getThumbnailUrl());
			param2.clear();
			param2.put("skuid", sku.getSkuid());
			param2.put("orgid", orgid);
			param2.put("deleteStatus", 0);
			List<OrgSku> orgSkus = orgSkuMapper.getOrgSkuSpecsByParam(param2);
			
			sku.setOrgSkuList(orgSkus);
		}
		goods.setSkuList(goodsSKUs);
		return goods;
	}
}

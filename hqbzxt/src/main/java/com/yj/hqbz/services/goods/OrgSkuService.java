package com.yj.hqbz.services.goods;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.goods.Goods;
import com.yj.hqbz.model.goods.GoodsSaleInfo;
import com.yj.hqbz.model.goods.OrgSku;
import com.yj.hqbz.model.org.Seller;
import com.yj.hqbz.model.user.UserInfo;

public interface OrgSkuService {
	OrgSku selectByPrimaryKey(Integer id);
	/**根据机构获取OrgSku*/
	PageInfo<OrgSku> getOrgSkuByOrg(Integer orgid,Integer page,Integer rows);
	
	PageInfo<OrgSku> getList(Map<String,Object> map,Integer page,Integer rows);
	
	int updateStatus(OrgSku sku);
	
	int deleteOrgSku(OrgSku sku);
	
	/**
	 * 根据商品编码和客户机构ID获取该商品的销售详情 (徐霞)
	 * @param goodsId  
	 * @param customerOrgId
	 * @return
	 */
	GoodsSaleInfo getGoodsSaleDetailInfo(String goodsid,Integer customerOrgId);
	
	/**
	 * 添加机构商品SKU
	 * @param seller
	 * @param user
	 */
	void insertGoodsSku(Seller seller,UserInfo user);
	
	/**
	 * 修改机构商品SKU
	 * @param seller
	 * @param user
	 */
	void updateGoodsSku(Seller seller,UserInfo user);
	/**
	 * 根据skuid获取商家在使用的供货商商品
	 * @param skuid
	 * @return
	 */
	List<OrgSku> getOrgSkuBySkuId(String skuid);
	
	/**
	 * 根据skuid 获取该该商家sku对应的销售规格
	 * @param skuid
	 * @param orgid
	 * @return
	 */
	List<OrgSku> getOrgSkuBySkuIdAndOrgId(String skuid,Integer orgid);
	/**
	 * @param goodsid
	 * @return
	 */
	List<OrgSku> getOrgSkuByGoodsId(String goodsid);
	/**
	 * @param param
	 * @return
	 */
	Goods getOrgSkuByGoodsidAndOrgid(Map<String, Object> param);
}

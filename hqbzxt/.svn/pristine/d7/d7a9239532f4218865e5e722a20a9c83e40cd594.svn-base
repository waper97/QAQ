package com.yj.hqbz.mapper.goods;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.goods.Goods;
import com.yj.hqbz.model.goods.OrgSku;

public interface OrgSkuMapper {
    OrgSku selectByPrimaryKey(Integer orgSkuid);
    /**根据机构获取OrgSku*/
	List<OrgSku> getOrgSkuByOrg(Integer orgid);
	
	List<OrgSku> getList(Map<String,Object> map);
	
	List<OrgSku> getOrgSkuSpecsByParam(Map<String,Object> map);
	
	int insertOrgSku(OrgSku sku);
	int updateOrgSku(OrgSku sku);
	
	int updateStatus(OrgSku sku);
	
	int deleteOrgSku(OrgSku sku);
	
	//根据买家和商品ID获取在售该商品的商家信息
	List<Map<String,Object>> selectSellerOrgByCustomerAndGoods(Map<String,Object> param);
	
	List<OrgSku> getOrgSkuBySkuId(String skuid);

	List<OrgSku> getOrgSkuByGoodsId(String goodsid);
	
	//根据商家skuid更新库存
	void updateOrgSkuQtyBySkuId(OrgSku sku);
}
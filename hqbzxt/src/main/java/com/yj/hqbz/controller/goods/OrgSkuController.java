package com.yj.hqbz.controller.goods;

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
import com.yj.hqbz.model.goods.Goods;
import com.yj.hqbz.model.goods.OrgSku;
import com.yj.hqbz.model.org.Seller;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.goods.GoodsService;
import com.yj.hqbz.services.goods.OrgSkuService;
import com.yj.hqbz.util.StringUtil;
@RestController
@RequestMapping("/goods/orgsku")
public class OrgSkuController extends BaseController{

	@Resource
	private OrgSkuService orgSkuService;
	@Resource
	private GoodsService goodsService;
	
	@RequestMapping("/getList")
	public Object getList(Integer typeid,String name,String spec,Integer orgid,String orgName,Integer status,DataGridModel model) {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("typeid", typeid);
		param.put("name", name);
		param.put("spec", spec);
		param.put("orgName", orgName);
		param.put("status", status);
		param.put("orderBy", model.getOrderBy());
		param.put("orderType", model.getOrderType());
		if(orgid==null){
            UserInfo user = getTokenUser();
            Integer org_id = user.getOrgId();
            if(org_id!=-1){
                param.put("orgid", org_id);
            }
        }else{
            param.put("orgid", orgid);
        }
		PageInfo<OrgSku> info = orgSkuService.getList(param,model.getPage(),model.getRows());
		return new BaseRes("获取成功！", info.getTotal(), info.getPages(), info.getList());
	}
	
	@RequestMapping("/updateStatus")
	public Object updateStatus(Integer orgSkuid,Integer status) {
		if(orgSkuid==null) {
			return fail("机构SKUID不能为空！");
		}
		if(status==null) {
			return fail("状态不能为空！");
		}
		//状态：0-正常，1-停用
		String str=status==0?"上架":"下架";
		OrgSku orgSku = orgSkuService.selectByPrimaryKey(orgSkuid);
		if(orgSku==null) {
			return fail("机构SKU不存在！");
		}
		if(orgSku.getDeleteStatus()==1) {
			return fail("机构SKU已删除，不允许"+str+"！");
		}
		if(orgSku.getStatus().intValue()==status.intValue()) {
			return fail("机构SKU已"+str+",不允许重复操作！");
		}
		
		orgSku.setStatus(status);
		orgSku.setLastOpUser(getTokenUser().getTrueName());
		orgSku.setLastOpDate(new Date());
		orgSkuService.updateStatus(orgSku);
		//日志
		saveJournalLog(str+"机构SKU", "id:"+orgSkuid);
		return success(str+"成功！");
	}
	
	@RequestMapping("/deleteOrgSku")
	public Object deleteOrgSku(Integer orgSkuid) {
		if(orgSkuid==null) {
			return fail("机构SKUID不能为空！");
		}
		OrgSku orgSku = orgSkuService.selectByPrimaryKey(orgSkuid);
		if(orgSku==null) {
			return fail("机构SKU不存在！");
		}
		if(orgSku.getStatus()==0) {
			return fail("机构SKU已上架，不允许删除！");
		}
		if(orgSku.getDeleteStatus()==1) {
			return fail("机构SKU已删除，不允许重复操作！");
		}
		
		orgSku.setDeleteStatus(1);;
		orgSku.setLastOpUser(getTokenUser().getTrueName());
		orgSku.setLastOpDate(new Date());
		orgSkuService.deleteOrgSku(orgSku);
		//日志
		saveJournalLog("删除机构SKU", "id:"+orgSkuid);
		return success("删除成功！");
	}
	
	/**
	 * 增加机构商品SKU销售规格
	 * @param seller
	 * @return
	 */
	@RequestMapping("/addOrgSku")
	public Object addSaveOrgSku(@RequestBody Seller seller){
		if(seller == null || seller.getOrgid() == null || seller.getSkuList()==null){
			return fail("参数非法!");
		}
		else{
			orgSkuService.insertGoodsSku(seller,getTokenUser());
			saveJournalLog("增加机构商品SKU销售规格信息", "");
			return success("保存成功！");
		}
	}
	
	/**
	 * 修改保存商家SKUID
	 * @param seller
	 * @return
	 */
	@RequestMapping("/updateOrgSku")
	public Object updateOrgSku(@RequestBody Seller seller){
		if(seller == null || seller.getOrgid() == null || seller.getSkuList()==null){
			return fail("参数非法!");
		}
		else{
			orgSkuService.updateGoodsSku(seller,getTokenUser());
			saveJournalLog("修改机构商品SKU销售规格信息", "");
			return success("保存成功！");
		}
	}
	
	/**
	 * 根据商品id、商家id获取商品的机构sku信息
	 * @param goodsid
	 * @param orgid
	 * @return
	 */
	@RequestMapping("/getOrgSkuList")
	public Object getOrgSkuList(String goodsid,String orgid){
		if(StringUtil.isBlank(goodsid)||StringUtil.isBlank(orgid)){
			return fail("商品id或商家id不能为空！");
		}
		Map<String, Object> param=new HashMap<>();
		param.put("goodsid", goodsid);
		param.put("orgid", orgid);
		Goods goods=orgSkuService.getOrgSkuByGoodsidAndOrgid(param);
		if(goods==null){
			return fail("商品信息不存在！");
		}
		return success(goods);
	}
	
}
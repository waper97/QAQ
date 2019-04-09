package com.yj.hqbz.controller.goods;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yj.common.controller.BaseController;
import com.yj.hqbz.model.goods.Goods;
import com.yj.hqbz.model.goods.GoodsPic;
import com.yj.hqbz.model.goods.GoodsType;
import com.yj.hqbz.services.goods.GoodsPicInfoService;
import com.yj.hqbz.services.goods.GoodsService;
import com.yj.hqbz.services.goods.GoodsTypeService;
import com.yj.hqbz.util.AsposeUtil;
import com.yj.hqbz.util.FileUtil;
import com.yj.hqbz.util.ImageUtil;
import com.yj.hqbz.util.StaticUtils;
import com.yj.hqbz.util.StringUtil;
import com.yj.hqbz.util.UUIDUtil;
import com.yj.hqbz.util.XlsReadUtil;
import com.yj.hqbz.web.Global;

@RestController
public class GoodsImportController extends BaseController{

	@Resource
	GoodsService goodsService;	
	@Resource
	GoodsPicInfoService goodsPicInfoService;	
	@Resource
	GoodsTypeService goodsTypeService;
	
	static final  String path="F:/商品汇总表";
	/**
	 * 导入商品信息[未完]
	 * @return
	 */
	@PostMapping("/goods/importGoodsXls")
	public Object importGoods(@RequestParam(value="hqfile") MultipartFile hqfile){
		XlsReadUtil xlsReader = new XlsReadUtil();
		String fileName = hqfile.getOriginalFilename();
		String msg = FileUtil.checkFileIsValid(fileName, ".XLS,.XLSX"); 
		if(msg!=null && !"".equals(msg)){
			return fail(msg);
		}
		else {
			try {
				List list = xlsReader.readExcel(fileName, hqfile.getInputStream());				
				if(list != null && list.size()>0){
					int size = list.size();
					for( int i = 0 ;i<size; ++i){
						Map item = (HashMap)list.get(i);
						String goodsNo 	= MapUtils.getString(item,"columns_0").trim();  //商品序号
						String specNo	= MapUtils.getString(item, "columns_1").trim(); //规格序号
						String businessAtt = MapUtils.getString(item, "columns_2").trim(); //业务属性
						String goodsTypeNF = MapUtils.getString(item, "columns_3").trim(); //一级分类名称
						String goodsTypeAF = MapUtils.getString(item, "columns_4").trim();  //一级分类别名
						String goodsTypeNS = MapUtils.getString(item, "columns_5").trim();  //二级分类名称
						String goodsTypeAS = MapUtils.getString(item, "columns_6").trim();  //二级分类别名
						String goodsName 	= MapUtils.getString(item, "columns_7").trim(); //商品名称
						String goodsAlias 	= MapUtils.getString(item, "columns_8").trim(); //商品别名
						String goodsUnit	= MapUtils.getString(item, "columns_9").trim(); //商品基本单位
						String goodsUnitDef = MapUtils.getString(item, "columns_10").trim(); //自定义商品基本单位
						String remark	= MapUtils.getString(item, "columns_11").trim(); //商品描述
						String specName		= MapUtils.getString(item, "columns_12").trim(); //规格名称
						String specRemark	= MapUtils.getString(item, "columns_13").trim(); //规格描述
						
						
						
						//获取商品类型（没有则新增）
						GoodsType goodsType = getGoodsTypeInfo(goodsTypeNF, goodsTypeAF, goodsTypeNS, goodsTypeAS, businessAtt);
						//获取商品（没有则新增）
						String unit=StringUtil.isBlank(goodsUnit)||"自定义".equals(goodsUnit)?goodsUnitDef:goodsUnit;
						Goods goods = getGoods(goodsNo,goodsType.getTypeid(), goodsName, goodsAlias, unit, remark);
						//添加规格
					
					}
				}
				return null;
			}
			catch(Exception ex){
				return fail(ex.getMessage());
			}
		}
	}
	
	
	
	
	/**
	 * 处理商品分类信息
	 * @param firstGoodsTypeName
	 * @param firstGoodsAliasName
	 * @param secondGoodsTypeName
	 * @param secondGoodsTypeAliasName
	 * @param businessAtt 商品业务属性
	 * @return
	 */
	private GoodsType getGoodsTypeInfo(String firstGoodsTypeName,String firstGoodsAliasName,
			String secondGoodsTypeName,String secondGoodsTypeAliasName,String businessAtt){
		return null;
	}
	
	
	/**获取商品*/
	private Goods getGoods(String goodsNo,Integer goodsTypeid, String goodsName,String goodsAlias,String unit,String remark) {
		Goods goods = goodsService.getGoodsByName(goodsName);
		if(goods==null) {
			goods=new Goods();
			goods.setGoodsid(UUIDUtil.getUUID());
			goods.setGoodsTypeid(goodsTypeid);
			goods.setGoodsName(goodsName);
			goods.setAliasName(goodsAlias);
			goods.setNamepy(getPinYin(null, goods.getGoodsName()));
			goods.setRemark(remark);
			goods.setStatus(0);
			goods.setCreator(getTokenUser().getTrueName());//创建人
			goods.setCreateDate(new Date());
			String details= AsposeUtil.word2Html(getDetailsUrlByDoc(goodsNo));//商品说明 (需要读取word文件)
			goods.setDetails(details);
			//新增商品
			goodsService.addImportGoods(goods);
			//添加图片
			addSKUPIC(goodsNo,null,goods.getGoodsid(),null);
		}
		return goods;
	}
	
	//添加图片
	private void addSKUPIC(String goodsNo,String specNo,String goodsid,String skuid) {
		//获取URL
		Map<String, Object> urlMap = getPicUrlByImg(goodsNo, specNo);
		//添加
		GoodsPic pic =new GoodsPic();
		pic.setGoodsid(goodsid);
		pic.setSkuid(skuid);
		pic.setPicUrl(urlMap.get("url").toString());
		pic.setThumbnailUrl(urlMap.get("smallUrl").toString());
		goodsPicInfoService.addGoodsPic(pic);
	}
	
	
	private String getDetailsUrlByDoc(String goodsNo) {
		String url=path+"/"+goodsNo+".docx";
		File d=new File(path);
		if(d.exists()) {
			return url;
		}
		url=path+"/"+goodsNo+".DOCX";
		File d2=new File(path);
		if(d2.exists()) {
			return url;
		}
		url=path+"/"+goodsNo+".doc";
		File d3=new File(path);
		if(d3.exists()) {
			return url;
		}
		url=path+"/"+goodsNo+".DOC";
		File d4=new File(path);
		if(d4.exists()) {
			return url;
		}
		return "";
	}
	
	private Map<String,Object> getPicUrlByImg(String goodsNo,String specNo) {
		String name=StringUtil.isBlank(specNo)?goodsNo:goodsNo+"-"+specNo;
		File d=new File(path);
		if(d.exists()) {
			File[] listFiles = d.listFiles();
			for (File file : listFiles) {
				if(file.exists()) {
					String fileAllName = file.getName();
					String fileName = fileAllName.substring(0,fileAllName.lastIndexOf("."));
					String suffix=fileAllName.substring(fileAllName.lastIndexOf("."));
					if(StaticUtils.FILE_IMG_TYPE.contains(suffix)&&fileName.equals(name)) {
						String visitDir = Global.getSystemParamValueByKey(StaticUtils.S_VISIT_DIR_CODE);
						//返回url
						String smallUrl= fileName+ StaticUtils.IMG_SMALL_CODE+ suffix;
						Map<String,Object> map=new HashMap<String,Object>();
						map.put("url", visitDir+"/goods/import/"+fileAllName);
						map.put("smallUrl", visitDir+"/goods/import/"+smallUrl);
						return map;
					}
				}
			}
		}
		return null;
	}
	
	
	
	
	
	
	/**生成缩略图和HTML*/
	public static void main(String[] args) {
		File d=new File(path);
		if(d.exists()) {
			File[] listFiles = d.listFiles();
			for (File file : listFiles) {
				if(file.exists()) {
					String fileAllName = file.getName();
					String fileName = fileAllName.substring(0,fileAllName.lastIndexOf("."));
					String suffix=fileAllName.substring(fileAllName.lastIndexOf("."));
					if(StaticUtils.FILE_IMG_TYPE.contains(suffix)) {
						//缩略图
						String smallUrl= fileName+ StaticUtils.IMG_SMALL_CODE+ suffix;
						ImageUtil.createThumbnailSmall(file.getAbsolutePath(), path +"/"+ smallUrl, 200, 0);
						System.err.println("生成缩略图");
					}
				}
			}
			System.err.println("==========完成=========");
		}
	}
	
}

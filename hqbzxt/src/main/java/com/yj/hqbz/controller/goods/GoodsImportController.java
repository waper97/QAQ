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
import com.yj.hqbz.model.goods.GoodsSKU;
import com.yj.hqbz.model.goods.GoodsType;
import com.yj.hqbz.services.goods.GoodsPicInfoService;
import com.yj.hqbz.services.goods.GoodsSKUService;
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
public class GoodsImportController extends BaseController {

	@Resource
	GoodsService goodsService;
	@Resource
	GoodsSKUService goodsSKUService;
	@Resource
	GoodsPicInfoService goodsPicInfoService;
	@Resource
	GoodsTypeService goodsTypeService;

	static final String path = "F:/商品汇总表";
//	static final String path = "F:/干副商品导入/65，88，91，216";

	/**
	 * 导入商品信息[未完]
	 * 
	 * @return
	 */
	@PostMapping("/goods/importGoodsXls")
	public Object importGoods(@RequestParam(value = "hqfile") MultipartFile hqfile) {
		XlsReadUtil xlsReader = new XlsReadUtil();
		String fileName = hqfile.getOriginalFilename();
		String msg = FileUtil.checkFileIsValid(fileName, ".XLS,.XLSX");
		if (msg != null && !"".equals(msg)) {
			return fail(msg);
		} else {
			int current=0;
			int successCount=0;
			int havedCount=0;
			int failCount=0;
			try {
				List list = xlsReader.readExcel(fileName, hqfile.getInputStream());
				if (list != null && list.size() > 0) {
					int size = list.size();
					for (int i = 0; i < size; ++i) {
						current=i;
						Map item = (HashMap) list.get(i);
						String goodsNo = MapUtils.getString(item, "columns_0").trim(); // 商品序号
						String specNo = MapUtils.getString(item, "columns_1").trim(); // 规格序号
						String businessAtt = MapUtils.getString(item, "columns_2").trim(); // 业务属性
						String firstTypeName = MapUtils.getString(item, "columns_3").trim(); // 一级分类名称
						String firstTypeAliasName = MapUtils.getString(item, "columns_4").trim(); // 一级分类别名
						String secondTypeName = MapUtils.getString(item, "columns_5").trim(); // 二级分类名称
						String secondTypeAliasName = MapUtils.getString(item, "columns_6").trim(); // 二级分类别名
						String goodsName = MapUtils.getString(item, "columns_7").trim(); // 商品名称
						String goodsAlias = MapUtils.getString(item, "columns_8").trim(); // 商品别名
						String goodsUnit = MapUtils.getString(item, "columns_9").trim(); // 商品基本单位
						String goodsUnitDef = MapUtils.getString(item, "columns_10").trim(); // 自定义商品基本单位
						String remark = MapUtils.getString(item, "columns_11").trim(); // 商品描述
						String specName = MapUtils.getString(item, "columns_12").trim(); // 规格名称
						String specRemark = MapUtils.getString(item, "columns_13").trim(); // 规格描述

						// 获取商品二级类型（没有则新增）
						GoodsType goodsType = getGoodsTypeInfo(firstTypeName, firstTypeAliasName, secondTypeName, secondTypeAliasName,businessAtt);
						// 获取商品（没有则新增）
						String unit = StringUtil.isBlank(goodsUnit) || "自定义".equals(goodsUnit) ? goodsUnitDef : goodsUnit;
						Goods goods = getGoods(goodsNo, goodsType.getTypeid(), goodsName, goodsAlias, unit, remark);
						// 添加规格
						int count = saveSKU(goods, specName, specRemark, goodsNo, specNo);
						if(count==1) {
							successCount+=1;
						}else if(count==0) {
							havedCount+=1;
						}else {
							failCount+=1;
						}
					}
				}
				return success("总数据："+list.size()+"条,新增"+successCount+"条,已存在"+havedCount+"条,失败"+failCount+"条");
			} catch (Exception ex) {
				ex.printStackTrace();
				return fail("第"+current+"条数据错误，新增"+successCount+"条,已存在"+havedCount+"条,失败"+failCount+"条");
			}
		}
	}

	/**
	 * 处理商品分类信息
	 * 
	 * @param firstGoodsTypeName
	 * @param firstGoodsAliasName
	 * @param secondGoodsTypeName
	 * @param secondGoodsTypeAliasName
	 * @param businessAtt
	 *            商品业务属性
	 * @return
	 */
	private GoodsType getGoodsTypeInfo(String firstTypeName, String firstTypeAliasName,
			String secondTypeName, String secondTypeAliasName, String businessAtt) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 一级分类
		map.put("typeName", firstTypeName);
		GoodsType firstGoodsType = goodsTypeService.getGoodsTypeByNameAndParentid(map);
		if (firstGoodsType == null) {
			Integer businessAttr = "生鲜".equals(businessAtt) ? 0 : 1;
			firstGoodsType = addGoodsType(0, firstTypeName, firstTypeAliasName, businessAttr);
		}
		// 二级分类
		map.put("typeName", secondTypeName);
		map.put("parentid", firstGoodsType.getTypeid());
		GoodsType secondGoodsType = goodsTypeService.getGoodsTypeByNameAndParentid(map);
		if (secondGoodsType == null) {
			secondGoodsType = addGoodsType(firstGoodsType.getTypeid(), secondTypeName, secondTypeAliasName,firstGoodsType.getBusinessAttr());
		}
		return secondGoodsType;
	}

	private GoodsType addGoodsType(Integer parentid, String typeName, String aliasName, Integer businessAttr) {
		GoodsType type = new GoodsType();
		type.setParentid(parentid);
		type.setTypeName(typeName);
		type.setAliasName(aliasName);
		type.setBusinessAttr(businessAttr);
		type.setStatus(0);
		type.setCreator(getTokenUser().getTrueName());
		goodsTypeService.addGoodsType(type);
		return type;
	}

	/** 获取商品 */
	private Goods getGoods(String goodsNo, Integer goodsTypeid, String goodsName, String goodsAlias, String unit,
			String remark) {
		Goods goods = goodsService.getGoodsByName(goodsName);
		if (goods == null) {
			goods = new Goods();
			goods.setGoodsid(UUIDUtil.getUUID());
			goods.setGoodsTypeid(goodsTypeid);
			goods.setGoodsName(goodsName);
			goods.setAliasName(goodsAlias);
			goods.setNamepy(getPinYin(null, goods.getGoodsName()));
			goods.setUnit(unit);
			goods.setRemark(remark);
			goods.setStatus(0);
			goods.setCreator(getTokenUser().getTrueName());// 创建人
			goods.setCreateDate(new Date());
			String details = AsposeUtil.word2Html(getDetailsUrlByDoc(goodsNo));// 商品说明 (需要读取word文件)
			goods.setDetails(details);
			// 新增商品
			goodsService.addImportGoods(goods);
		}
		// 添加图片
		addSKUPIC(goodsNo, null, goods.getGoodsid(), null);
		return goods;
	}

	private int saveSKU(Goods goods, String specName, String specRemark, String goodsNo, String specNo) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("goodsid", goods.getGoodsid());
			map.put("specName", specName);
			GoodsSKU sku = goodsSKUService.getSkuByNameAndGoodsid(map);
			if(sku==null) {
				sku=new GoodsSKU();
				sku.setSkuid(UUIDUtil.getUUID());
				sku.setGoodsid(goods.getGoodsid());
				sku.setProperty(specName);
				sku.setSpec(specRemark);
				sku.setSortNo(Integer.valueOf(specNo));
				sku.setStatus(0);
				sku.setCreator(getTokenUser().getTrueName());// 创建人
				goodsSKUService.addGoodsSKU(sku);
				// 添加图片
				addSKUPIC(goodsNo, specNo, goods.getGoodsid(), sku.getSkuid());
				return 1;
			}else {
				System.out.println("SKU已存在=====序号：【"+goodsNo+"-"+specNo+"】,名称【"+goods.getGoodsName()+"】,"+"规格：【"+specName+"】");
				// 添加图片
				addSKUPIC(goodsNo, specNo, goods.getGoodsid(), sku.getSkuid());
				return 0;
			}
		} catch (Exception e) {
			System.err.println("SKU添加失败=====序号：【"+goodsNo+"-"+specNo+"】,名称【"+goods.getGoodsName()+"】,"+"规格：【"+specName+"】");
			e.printStackTrace();
			return -1;
		}
	}

	// 添加图片
	private void addSKUPIC(String goodsNo, String specNo, String goodsid, String skuid) {
		// 获取URL
		Map<String, Object> urlMap = getPicUrlByImg(goodsNo, specNo);
		if(urlMap!=null&&urlMap.size()>0) {
			// 添加
			GoodsPic pic = new GoodsPic();
			pic.setGoodsid(goodsid);
			pic.setSkuid(skuid);
			pic.setPicUrl(urlMap.get("url").toString().trim());
			pic.setThumbnailUrl(urlMap.get("smallUrl").toString().trim());
			GoodsPic oldPic = goodsPicInfoService.getGoodsPicByPic(pic);
			if(oldPic==null) {
				goodsPicInfoService.addGoodsPic(pic);
			}
		}else {
			System.err.println("图片不存在=====序号：【"+goodsNo+"-"+specNo+"】");
		}
	}

	private String getDetailsUrlByDoc(String goodsNo) {
		String url = path + "/" + goodsNo + ".docx";
		File d = new File(path);
		if (d.exists()) {
			return url;
		}
		url = path + "/" + goodsNo + ".DOCX";
		File d2 = new File(path);
		if (d2.exists()) {
			return url;
		}
		url = path + "/" + goodsNo + ".doc";
		File d3 = new File(path);
		if (d3.exists()) {
			return url;
		}
		url = path + "/" + goodsNo + ".DOC";
		File d4 = new File(path);
		if (d4.exists()) {
			return url;
		}else {
			System.err.println("序号为【"+goodsNo+"】的文档："+url+"不存在");
		}
		return "";
	}

	private Map<String, Object> getPicUrlByImg(String goodsNo, String specNo) {
		String name = StringUtil.isBlank(specNo) ? goodsNo : goodsNo + "-" + specNo;
		File d = new File(path);
		if (d.exists()) {
			File[] listFiles = d.listFiles();
			for (File file : listFiles) {
				if (file.exists()) {
					String fileAllName = file.getName();
					String fileName = fileAllName.substring(0, fileAllName.lastIndexOf("."));
					String suffix = fileAllName.substring(fileAllName.lastIndexOf("."));
					if (StaticUtils.FILE_IMG_TYPE.contains(suffix) && fileName.equals(name)) {
						String visitDir = Global.getSystemParamValueByKey(StaticUtils.S_VISIT_DIR_CODE);
						// 返回url
						String smallUrl = fileName + StaticUtils.IMG_SMALL_CODE + suffix;
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("url", visitDir + "/goods/import/" + fileAllName);
						map.put("smallUrl", visitDir + "/goods/import/" + smallUrl);
						return map;
					}
				}
			}
		}
		return null;
	}

	/** 生成缩略图和HTML */
	public static void main(String[] args) {
		File d = new File(path);
		if (d.exists()) {
			File[] listFiles = d.listFiles();
			for (File file : listFiles) {
				if (file.exists()) {
					String fileAllName = file.getName();
					String fileName = fileAllName.substring(0, fileAllName.lastIndexOf("."));
					String suffix = fileAllName.substring(fileAllName.lastIndexOf("."));
					if (StaticUtils.FILE_IMG_TYPE.contains(suffix)) {
						// 缩略图
						String smallUrl = fileName + StaticUtils.IMG_SMALL_CODE + suffix;
						ImageUtil.createThumbnailSmall(file.getAbsolutePath(), path + "/" + smallUrl, 200, 0);
						System.err.println("生成缩略图");
					}
				}
			}
			System.err.println("==========完成=========");
		}
	}

}

package com.yj.hqbz.controller.schoolOperation;

import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.hqbz.model.schoolOperation.FoodAdditive;
import com.yj.hqbz.services.schoolOperation.FoodAdditiveService;
import com.yj.hqbz.util.StringUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangpeng
 * @date 2019-3-14
 */
@RestController
public class FoodAdditiveController extends BaseController {

	@Resource
	FoodAdditiveService foodAdditiveService;

	/**
	 * 查询食品添加剂使用列表
	 *
	 * @param persionLiable 责任人名称
	 * @param useDate       使用日期
	 * @param menuType      餐食类别
	 * @param model
	 * @param aliable       责任人
	 * @return
	 */
	@GetMapping("/foodAddtive/school/getList")
	public Object getFoodAdditiveList(String persionLiable, String useDate, Integer menuType, String aliable, DataGridModel model) {
		Map<String, Object> param = new HashMap<>();
		param.put("persionLiable", persionLiable);
		param.put("useDate", useDate);
		param.put("menuType", menuType);
		param.put("aliable", aliable);
		PageInfo<FoodAdditive> pg = foodAdditiveService.getFoodAdditiveList(param, model.getPage(), model.getRows());
		return new BaseRes("ok", pg.getTotal(), pg.getPages(), pg.getList());
	}

	/**
	 * 条件查看食品添加剂使用记录
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/foodAddtive/school/getDetailInfo")
	public Object selectByPrimaryKey(String id) {
		if (StringUtil.isBlank(id))
			return new BaseRes("false");
		FoodAdditive rseult = foodAdditiveService.selectByPrimaryKey(id);
		return rseult;
	}

	/**
	 * 删除商品添加剂使用记录
	 *
	 * @param id
	 * @return
	 */
	@PostMapping("/foodAddtive/school/deleteRecord")
	public Object deleteByPrimaryKey(String id) {
		if (StringUtil.isBlank(id))
			return fail("参数不能为空");
		//TODO:判断记录是否存在
		FoodAdditive food = new FoodAdditive();
		food.setStatus(1);
		food.setId(id);
		food.setLastopdate(new Date());
		food.setLastopuser(getTokenUser().getTrueName());
		foodAdditiveService.updateByPrimaryKeySelective(food);
		//TODO:日志
		return success("删除成功");
	}

	/**
	 * 修改食品添加剂使用
	 *
	 * @param food
	 * @return
	 */
	@PostMapping("/foodAddtive/school/updateRecord")
	public Object updateSave(FoodAdditive food) {
		if (StringUtil.isBlank(food.getId()))
			return fail("食品添加剂使用记录id不能为空，修改失败");
		if (food.getUsedate() == null || food.getUsedate() == null || food.getLiableid() == null
				|| food.getQty() == null || food.getPercent() == null) {
			return fail("必填字段不能为空");
		}
		food.setLastopuser(getTokenUser().getTrueName());
		food.setLastopdate(new Date());
		foodAdditiveService.updateByPrimaryKeySelective(food);
		//日志
		return success("修改成功");
	}

	/**
	 * 新增食品添加剂使用记录
	 *
	 * @param food
	 * @return
	 */
	@RequestMapping("foodAddtive/school/saveRecord")
	public Object addSave(FoodAdditive food) {
		if (food.getFkOutdetailid() == null) {
			return fail("出库ID不能为空");
		}
		if (food.getUsedate() == null) {
			return fail("使用时间不能为空");
		}
		if (food.getMenutype() == null) {
			return fail("餐食类别不能为空");
		}
		//TODO:字段名称可能有问题，后面在调整
		if (food.getLiableid() == null) {
			return fail("责任人ID不能为空");
		}
		if (food.getPersionLiable() == null) {
			return fail("责任人名称不能为空");
		}
		if (food.getQty() == null) {
			return fail("使用量不能空");
		}
		if (food.getPercent() == null) {
			return fail("使用百分比不能为空");
		}
		food.setStatus(0);
		food.setCreatedate(new Date());
		food.setCreator(getTokenUser().getTrueName());
		//设置id
		food.setId(StringUtil.getUUID());
		foodAdditiveService.insert(food);
		//记录日志
		return success("添加成功");
	}

	/**
	 * 查询使用详情
	 * @param code
	 * @return
	 */
	@GetMapping("/foodAddtive/school/getUseDetailInfo")
	public Object foodAdditiveMapper(String code) {
		if (StringUtil.isBlank(code)) {
			return fail("使用记录ID不能为空");
		}
		Map<String, Object> result = foodAdditiveService.getFoodAdditiveUseDetail(code);
		System.out.println(result);
		return result;
	}

	@PostMapping("/foodAddtive/school/upload")
	public String upload(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return "不能为空";
		}
		String fileName = file.getOriginalFilename();
		String filePath = "D:\\";

		File f = new File(filePath, fileName);
		try {
			file.transferTo(f);
			return "上传成功";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "上传失败！";
	}

	public static void main(String[] args) {

	}
}

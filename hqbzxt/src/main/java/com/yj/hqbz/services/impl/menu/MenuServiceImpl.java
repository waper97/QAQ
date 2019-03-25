package com.yj.hqbz.services.impl.menu;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.menu.MenuDishMapper;
import com.yj.hqbz.mapper.menu.MenuIndexMapper;
import com.yj.hqbz.mapper.menu.MenuMainMaterialMapper;
import com.yj.hqbz.mapper.schoolOperation.DiningOutMapper;
import com.yj.hqbz.model.menu.MenuDish;
import com.yj.hqbz.model.menu.MenuIndex;
import com.yj.hqbz.model.menu.MenuMainMaterial;
import com.yj.hqbz.model.schoolOperation.DiningOut;
//import com.yj.hqbz.mapper.menu.MenuDishMapper;
//import com.yj.hqbz.mapper.menu.MenuIndexMapper;
//import com.yj.hqbz.mapper.menu.MenuMainMaterialMapper;
//import com.yj.hqbz.model.menu.MenuIndex;
import com.yj.hqbz.services.menu.MenuService;
import com.yj.hqbz.util.StringUtil;

@Service
public class MenuServiceImpl implements MenuService{

	@Resource
	private MenuIndexMapper menuIndexMapper;
	@Resource
	private MenuDishMapper menuDishMapper;
	@Resource
	private MenuMainMaterialMapper menuMainMaterialMapper;
	@Resource
	DiningOutMapper diningOutMapper;
	
	public MenuIndex selectByPrimaryKey(String menuid) {
		return menuIndexMapper.selectByPrimaryKey(menuid);
	}
	
	public PageInfo<MenuIndex> getMenuList(Map<String, Object> param, int page, int rows) {
		PageHelper.startPage(page, rows);
		List<MenuIndex> list = menuIndexMapper.getMenuList(param);
		for (MenuIndex menuIndex : list) {
			menuIndex.setDetail(menuDishMapper.getDishByMenu(menuIndex.getMenuid()));
		}
		PageInfo<MenuIndex> info=new PageInfo<MenuIndex>(list);
		return info;
	}
	
	@Transactional
	public int addSave(MenuIndex menu) {
		int count = menuIndexMapper.addSave(menu);
		if(count>0) {
			addDetail(menu);
		}
		return count;
	}
	
	@Transactional
	public int updateSave(MenuIndex menu) {
		int count = menuIndexMapper.updateSave(menu);
		if(count>0) {
			menuMainMaterialMapper.deleteMaterialByMenu(menu.getMenuid());
			menuDishMapper.deleteDishByMenu(menu.getMenuid());
			addDetail(menu);
		}
		return 0;
	}
	
	private void addDetail(MenuIndex menu) {
		for(MenuDish dish:menu.getDetail()) {
			dish.setDishid(StringUtil.getUUID());
			dish.setMenuid(menu.getMenuid());
			dish.setCreator(menu.getCreator());
			dish.setCreateDate(menu.getCreateDate());
			int i = menuDishMapper.addDish(dish);
			if(i>0) {
				addMaterial(dish);
			}
		}
	}
	
	private void addMaterial(MenuDish dish) {
		for(MenuMainMaterial material:dish.getMaterial()) {
			material.setId(StringUtil.getUUID());
			material.setMenuid(dish.getMenuid());
			material.setDishid(dish.getDishid());
			menuMainMaterialMapper.addMaterial(material);
		}
	}
	
	
	public MenuIndex getMenuInfo(String menuid) {
		MenuIndex menu = menuIndexMapper.selectByPrimaryKey(menuid);
		if(menu!=null) {
			List<MenuDish> dishList = menuDishMapper.getDishByMenu(menuid);
			if(dishList!=null&&dishList.size()>0) {
				for (MenuDish dish : dishList) {
					dish.setMaterial(menuMainMaterialMapper.getMaterialByDish(dish.getDishid()));
				}
				menu.setDetail(dishList);
			}
		}
		return menu;
	}
	
	@Transactional
	public int checkBill(MenuIndex menu) {
		menuIndexMapper.checkBill(menu);
		//自动生成出餐记录
		if(menu.getStatus().intValue()==2){
			List<MenuDish> dishList = menuDishMapper.getDishByMenu(menu.getMenuid());
			for(MenuDish dish:dishList){				
				DiningOut out = new DiningOut();
				out.setCreator(dish.getCreator());
				out.setCreateDate(new Date());
				out.setOutDate(new Date());
				out.setDeleteStatus(0);
				out.setDishName(dish.getDishName());
				out.setMenuType(menu.getMenuType());
				out.setOrgid(menu.getOrgid());
				out.setMaterialName(getDishMaterial(dish.getDishid()));
				out.setStatus(0);
				out.setMenuCode(menu.getMenuCode());
				out.setId(StringUtil.getUUID());
				diningOutMapper.insert(out);
			}
		}
		return 1;
	}
	
	private String getDishMaterial(String dishid){
		StringBuffer buff = new StringBuffer();
		List<MenuMainMaterial> material = menuMainMaterialMapper.getMaterialByDish(dishid);
		for(MenuMainMaterial m:material){
			buff.append(m.getMaterialName()+" ");
		}
		return buff.toString();
	}
	
	@Transactional
	public int deleteBill(MenuIndex menu) {
		return menuIndexMapper.deleteBill(menu);
	}
}

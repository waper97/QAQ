package com.yj.hqbz.services.menu;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.menu.MenuIndex;

public interface MenuService {
	MenuIndex selectByPrimaryKey(String menuid);

	PageInfo<MenuIndex> getMenuList(Map<String,Object> param,int page,int rows);
	
	MenuIndex getMenuInfo(String menuid);
	
	int addSave(MenuIndex menu);
	
	int updateSave(MenuIndex menu);
	
	int checkBill(MenuIndex menu);
	
	int deleteBill(MenuIndex menu);
}

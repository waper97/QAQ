package com.yj.hqbz.mapper.menu;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.menu.MenuIndex;
import com.yj.hqbz.model.menu.MenuInfo;

public interface MenuIndexMapper {
    MenuIndex selectByPrimaryKey(String menuid);
    
    List<MenuIndex> getMenuList(Map<String,Object> param);

    List<MenuInfo> getMenuInfoAndMenuDishAndMaterial(String menuid);
    
    int addSave(MenuIndex menu);
    
    int updateSave(MenuIndex menu);
    
	int checkBill(MenuIndex menu);
	
	int deleteBill(MenuIndex menu);
}
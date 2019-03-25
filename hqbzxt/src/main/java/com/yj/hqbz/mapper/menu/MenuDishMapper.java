package com.yj.hqbz.mapper.menu;

import java.util.List;

import com.yj.hqbz.model.menu.MenuDish;

public interface MenuDishMapper {
    MenuDish selectByPrimaryKey(String dishid);
    
    List<MenuDish> getDishByMenu(String menuid);
    
    int addDish(MenuDish dish);
    
    int deleteDishByMenu(String menuid);
}
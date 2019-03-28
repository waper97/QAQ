package com.yj.hqbz.model.menu;
/**
 * @Title  
 * @Description:    菜品信息
 * @Author:         wangpeng
 * @CreateDate:     2019/3/28 14:23
 */
public class MenuInfo {
    //菜品明细id
    private String dishid;
    //菜单id
    private String menuid;
    //组织编号
    private String orgid;
    //菜单编号
    private String menuCode;
    //餐食类别
    private String menuType;

    private String mealsDate;
    //菜名
    private String dishName;

    private String cookerid;
    //厨师
    private String cookerName;
    //材料
    private String materialName;

    private String creator;

    private String creatDate;

    public String getDishid() {
        return dishid;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public void setDishid(String dishid) {
        this.dishid = dishid;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }



    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMealsDate() {
        return mealsDate;
    }

    public void setMealsDate(String mealsDate) {
        this.mealsDate = mealsDate;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getCookerid() {
        return cookerid;
    }

    public void setCookerid(String cookerid) {
        this.cookerid = cookerid;
    }

    public String getCookerName() {
        return cookerName;
    }

    public void setCookerName(String cookerName) {
        this.cookerName = cookerName;
    }
}

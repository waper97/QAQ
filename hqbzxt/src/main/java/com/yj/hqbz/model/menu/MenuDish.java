package com.yj.hqbz.model.menu;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MenuDish {
    private String dishid;

    private String menuid;

    private String dishName;

    private String cookerid;

    private String cookerName;

    private String creator;

    @JsonFormat(pattern="yyyy-MM-dd HH:MM:ss",timezone = "GMT+8")
    private Date createDate;

    private List<MenuMainMaterial> material;

	public String getDishid() {
		return dishid;
	}

	public void setDishid(String dishid) {
		this.dishid = dishid;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<MenuMainMaterial> getMaterial() {
		return material;
	}

	public void setMaterial(List<MenuMainMaterial> material) {
		this.material = material;
	}
    
	

    
}
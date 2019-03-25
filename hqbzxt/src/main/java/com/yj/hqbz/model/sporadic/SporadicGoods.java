package com.yj.hqbz.model.sporadic;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SporadicGoods {
    private String goodsid;

    private String code;

    private Integer goodsTypeid;

    private String goodsName;

    private String namepy;

    private String unit;

    private Integer orgid;

    private String creator;
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private String lastOpUser;
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastOpDate;


    public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
	public Integer getGoodsTypeid() {
		return goodsTypeid;
	}

	public void setGoodsTypeid(Integer goodsTypeid) {
		this.goodsTypeid = goodsTypeid;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLastOpUser() {
		return lastOpUser;
	}

	public void setLastOpUser(String lastOpUser) {
		this.lastOpUser = lastOpUser;
	}

	public Date getLastOpDate() {
		return lastOpDate;
	}

	public void setLastOpDate(Date lastOpDate) {
		this.lastOpDate = lastOpDate;
	}

    public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getNamepy() {
        return namepy;
    }

    public void setNamepy(String namepy) {
        this.namepy = namepy;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

}
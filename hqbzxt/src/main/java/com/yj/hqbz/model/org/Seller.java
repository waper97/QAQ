package com.yj.hqbz.model.org;

import java.util.List;

import com.yj.hqbz.model.goods.OrgSku;

/**  
 * @Title: Seller.java
 * @Package com.yj.hqbz.model.org
 * @Description: TODO
 * @author xx
 * @date 2019-3-6
 */
public class Seller {

	private Integer orgid;
	private String orgName;
	
	 private String licensePic;

	 private String licensekeyPic;

	 private String tradeMark;

	 private String ratePaying;
	
	private List<OrgSku> skuList;

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public List<OrgSku> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<OrgSku> skuList) {
		this.skuList = skuList;
	}

	public String getLicensePic() {
		return licensePic;
	}

	public void setLicensePic(String licensePic) {
		this.licensePic = licensePic;
	}

	public String getLicensekeyPic() {
		return licensekeyPic;
	}

	public void setLicensekeyPic(String licensekeyPic) {
		this.licensekeyPic = licensekeyPic;
	}

	public String getTradeMark() {
		return tradeMark;
	}

	public void setTradeMark(String tradeMark) {
		this.tradeMark = tradeMark;
	}

	public String getRatePaying() {
		return ratePaying;
	}

	public void setRatePaying(String ratePaying) {
		this.ratePaying = ratePaying;
	}
	

}

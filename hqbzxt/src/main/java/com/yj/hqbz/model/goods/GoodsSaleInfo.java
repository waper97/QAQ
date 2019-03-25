package com.yj.hqbz.model.goods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yj.hqbz.model.org.Seller;

/**
 * 商品销售详情
 * @Title: GoodsSaleInfo.java
 * @Package com.yj.hqbz.model.goods
 * @Description: TODO
 * @author xx
 * @date 2019-3-6
 */
public class GoodsSaleInfo extends Goods {
    private List<Seller> seller;

	public List<Seller> getSeller() {
		return seller;
	}

	public void setSeller(List<Seller> seller) {
		this.seller = seller;
	}
    
}